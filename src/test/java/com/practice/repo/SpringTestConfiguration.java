package com.practice.repo;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@CucumberContextConfiguration
@ContextConfiguration(classes = SpringComponentConfiguration.class)
public class SpringTestConfiguration extends AbstractTestNGSpringContextTests {
}