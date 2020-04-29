package com.github.ramasct1.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for injecting params that were captured using {@link CaptureRequestParams}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InjectRequestParams {

    /**
     * position of a method argument
     *
     * @return
     */
    int position() default 0;

    /**
     * param names that have to be injected.
     *
     * @return
     */
    String[] paramNames();
}
