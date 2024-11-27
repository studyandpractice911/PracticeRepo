package com.practice.repo;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest extends SpringTestConfiguration {

    @Autowired
    protected ComponentManager componentManager;

    @BeforeSuite
    public void prepareSuite() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterMethod
    public void closeDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            closeWebDriver();
        }
    }
}