package com.practice.repo;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import io.cucumber.junit.CucumberOptions;

@CucumberContextConfiguration
@ContextConfiguration(classes = SpringComponentConfiguration.class)
@CucumberOptions(plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class SpringTestConfiguration extends AbstractTestNGSpringContextTests {
}