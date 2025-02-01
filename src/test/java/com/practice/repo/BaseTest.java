package com.practice.repo;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest extends SpringTestConfiguration implements IRetryAnalyzer {

    @Autowired
    protected ComponentManager componentManager;
    protected int retryCount = 0;

    @BeforeSuite
    public void addListener() {
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

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount<2) {
            retryCount++;
            return true;
        } else return false;
    }
}