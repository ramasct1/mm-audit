package com.github.ramasct1.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for capturing params from request headers.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CaptureRequestParams {

    /**
     * Param names that have to be captured from request headers
     *
     * @return
     */
    ParamField[] paramsNames();
}
