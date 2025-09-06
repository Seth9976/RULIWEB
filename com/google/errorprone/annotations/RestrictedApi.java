package com.google.errorprone.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface RestrictedApi {
    String allowedOnPath() default "";

    Class[] allowlistAnnotations() default {};

    Class[] allowlistWithWarningAnnotations() default {};

    String explanation();

    String link();
}

