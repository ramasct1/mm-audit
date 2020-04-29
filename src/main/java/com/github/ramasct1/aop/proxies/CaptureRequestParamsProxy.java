package com.github.ramasct1.aop.proxies;

import com.github.ramasct1.aop.annotations.CaptureRequestParams;
import com.github.ramasct1.aop.annotations.ParamField;
import com.github.ramasct1.aop.errors.BadRequestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A cross cutting concern implementation for {@link CaptureRequestParams}
 */
@Aspect
@Component
public class CaptureRequestParamsProxy {

    private final RequestParamsHolder requestParamsHolder;

    @Autowired
    public CaptureRequestParamsProxy(final RequestParamsHolder requestParamsHolder) {
        this.requestParamsHolder = requestParamsHolder;
    }

    @Pointcut("@annotation(captureRequestParams)")
    public void perform(CaptureRequestParams captureRequestParams) {
    }

    @Before("perform(captureRequestParams)")
    public void interceptRequestParams(JoinPoint joinPoint, CaptureRequestParams captureRequestParams) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ParamField[] paramNames = captureRequestParams.paramsNames();
        Arrays.stream(paramNames).forEach(paramName -> {
            Optional<String> value = Optional.ofNullable(request.getHeader(paramName.fieldName()));
            if (value.isPresent()) {
                requestParamsHolder.setParam(paramName.fieldName(), value.get());
            } else if (paramName.required()) {
                throw new BadRequestException(String.format("%s is missing and marked as required field", paramName.fieldName()));
            }

        });

    }

    @Component
    @RequestScope
    public static class RequestParamsHolder {

        private final Map<String, String> requestParams;

        public RequestParamsHolder() {
            this.requestParams = new HashMap<>();
        }

        public Optional<String> getParam(String paramName) {
            return Optional.ofNullable(requestParams.get(paramName));
        }

        public void setParam(String name, String value) {
            requestParams.put(name, value);
        }

    }
}
