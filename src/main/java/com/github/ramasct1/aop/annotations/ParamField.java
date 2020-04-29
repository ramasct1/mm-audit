package com.github.ramasct1.aop.annotations;


import com.github.ramasct1.aop.errors.BadRequestException;

/**
 * Represents an entry for paramsNames in {@link CaptureRequestParams}
 */
public @interface ParamField {

    /**
     * Header name present in request
     *
     * @return
     */
    String fieldName();

    /**
     * Throws {@link BadRequestException} if required and not present in request headers.
     * Defaults to true
     *
     * @return
     */
    boolean required() default true;
}
