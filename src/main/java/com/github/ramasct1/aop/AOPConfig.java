package com.github.ramasct1.aop;

import com.github.ramasct1.aop.proxies.CaptureRequestParamsProxy;
import com.github.ramasct1.aop.proxies.InjectRequestParamsProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AOPConfig {

    @Bean
    CaptureRequestParamsProxy myAspect(CaptureRequestParamsProxy.RequestParamsHolder requestParamsHolder) {
        return new CaptureRequestParamsProxy(requestParamsHolder);
    }

    @Bean
    CaptureRequestParamsProxy.RequestParamsHolder requestParamsHolder() {
        return new CaptureRequestParamsProxy.RequestParamsHolder();
    }

    @Bean
    InjectRequestParamsProxy injectRequestParams(CaptureRequestParamsProxy.RequestParamsHolder requestParamsHolder) {
        return new InjectRequestParamsProxy(requestParamsHolder);
    }

}
