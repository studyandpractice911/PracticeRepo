package com.practice.repo.utils;

import static com.practice.repo.enums.UserAgent.DESKTOP;

import com.practice.repo.enums.ResourceType;
import com.practice.repo.enums.UserAgent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {

    String path() default "";
    ResourceType type();
    UserAgent userAgent() default DESKTOP;
}
