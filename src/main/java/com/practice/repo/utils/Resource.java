package com.practice.repo.utils;

import com.practice.repo.enums.ResourceType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {

    String path() default "";
    ResourceType type();
}
