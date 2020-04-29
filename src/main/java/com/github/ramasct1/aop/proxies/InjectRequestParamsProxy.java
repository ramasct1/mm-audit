package com.github.ramasct1.aop.proxies;


import com.github.ramasct1.aop.annotations.InjectRequestParams;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A cross cutting concern for {@link InjectRequestParams} annotation
 */
@Aspect
@Component
public class InjectRequestParamsProxy {

    private final CaptureRequestParamsProxy.RequestParamsHolder requestParamsHolder;

    @Autowired
    public InjectRequestParamsProxy(final CaptureRequestParamsProxy.RequestParamsHolder requestParamsHolder) {
        this.requestParamsHolder = requestParamsHolder;
    }

    @Around("@annotation(injectRequestParams)")
    public Object injectRequestParams(ProceedingJoinPoint proceedingJoinPoint, InjectRequestParams injectRequestParams) throws Throwable {
        Object valueToReturn = null;
        Object[] arguments = proceedingJoinPoint.getArgs();

        int position = injectRequestParams.position() - 1;

        Map<String, String> requestedParams = new HashMap<>(injectRequestParams.paramNames().length);

        Arrays.stream(injectRequestParams.paramNames()).forEach(paramName -> {
            Optional<String> optionalValue = this.requestParamsHolder.getParam(paramName);
            requestedParams.put(paramName, optionalValue.isPresent() ? optionalValue.get() : "");
        });

        if (arguments.length >= position) {
            arguments[position] = requestedParams;
        } else {
            throw new RuntimeException(String.format("Invalid Position %d", position));
        }
        valueToReturn = proceedingJoinPoint.proceed(arguments);

        return valueToReturn;

    }
}
