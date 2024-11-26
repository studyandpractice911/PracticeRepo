package com.practice.repo;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Component
public class BaseComponent extends SpringComponentConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BaseComponent.class);

    @Autowired
    Environment environment;

    private void resolveUrl(String packageName, String resourcePath) {
        String URL;
        String baseUrlKey = packageName.concat(".base.url");
        try{
            String baseUrl = Objects.requireNonNull(environment.getProperty(baseUrlKey));
            URL = baseUrl.concat(resourcePath);
        }catch (NullPointerException e){
            throw new NullPointerException("Please define baseUrl in property file like this : " + baseUrlKey);
        }
        log.info("Resolved URL is {}", URL);
        System.setProperty("base.url", URL);
    }

    private WebDriver configureDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(List.of(
                "--start-maximized",
                "--disable-notifications",
                "--ignore-certificate-errors"
        ));
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        return driver;
    }

    public void launchDriver(String packageName, String resourcePath) {
        resolveUrl(packageName, resourcePath);
        if (!WebDriverRunner.hasWebDriverStarted()) {
            WebDriver driver = configureDriver();
            WebDriverRunner.setWebDriver(driver);
        }
        Selenide.open(System.getProperty("base.url"));
    }

    public void requestSpecification(String packageName, String resourcePath) {
        resolveUrl(packageName, resourcePath);
    }

    protected RequestSpecification request() {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .baseUri(System.getProperty("base.url"))
                .log().all();
    }

}
