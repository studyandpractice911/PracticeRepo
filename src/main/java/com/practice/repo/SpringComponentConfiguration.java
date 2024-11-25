package com.practice.repo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
//@PropertySource("classpath:${test.config}.properties")
@PropertySource("classpath:default.properties")
public class SpringComponentConfiguration {
}