package com.practice.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:${property.filename:default}.properties")
public class SpringComponentConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SpringComponentConfiguration.class);

    public SpringComponentConfiguration() {
        if(System.getProperty("property.filename")==null){
            log.error("Could not resolve property file for key : [{}]", "property.filename");
            log.warn("Using default property file : [default.properties]");
        }
        else log.info("Using property file : [{}.properties]", System.getProperty("property.filename"));
    }
}