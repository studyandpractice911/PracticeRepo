package com.practice.repo;

import static io.restassured.filter.log.LogDetail.ALL;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.practice.repo.utils.Resource;
import com.practice.repo.utils.WebDriverHandler;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@Component
public class BaseComponent extends SpringComponentConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BaseComponent.class);

    @Autowired
    Environment environment;

    @Autowired
    WebDriverHandler webDriverHandler;

    private void resolveUrl(String packageName, String resourcePath) {
        String URL;
        String baseUrlKey = packageName.concat(".base.url");
        try {
            String baseUrl = Objects.requireNonNull(environment.getProperty(baseUrlKey));
            URL = baseUrl.concat(resourcePath);
        } catch (NullPointerException e) {
            throw new NullPointerException("Please define baseUrl in property file like this : " + baseUrlKey);
        }
        log.info("Current URL is {}", URL);
        System.setProperty("base.url", URL);
    }

    public void launchWebDriver(String packageName, String resourcePath, Resource resource) {
        resolveUrl(packageName, resourcePath);
        if (!WebDriverRunner.hasWebDriverStarted()) {
            WebDriver driver = webDriverHandler.configureWebDriver(resource);
            WebDriverRunner.setWebDriver(driver);
        }
        Selenide.open(System.getProperty("base.url"));
    }

    public void launchAndroidDriver(String packageName, String resourcePath) {
        resolveUrl(packageName, resourcePath);
        if (!WebDriverRunner.hasWebDriverStarted()) {
            try {
                WebDriver driver = webDriverHandler.configureAndroidDriver();
                WebDriverRunner.setWebDriver(driver);
            } catch (MalformedURLException malformedURLException) {
                log.error(malformedURLException.getMessage());
            }
        }
    }

    public void requestSpecification(String packageName, String resourcePath) {
        resolveUrl(packageName, resourcePath);
    }

    protected RequestSpecification restAssured() {

        List<Filter> filters = List.of(
                new AllureRestAssured(),
                new RequestLoggingFilter(ALL),
                new ResponseLoggingFilter(ALL));

        return RestAssured.given()
                .filters(filters)
                .baseUri(System.getProperty("base.url"));
    }

}
