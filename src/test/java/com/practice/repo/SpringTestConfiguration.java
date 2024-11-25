package com.practice.repo;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = SpringComponentConfiguration.class)
public class SpringTestConfiguration extends AbstractTestNGSpringContextTests {
}