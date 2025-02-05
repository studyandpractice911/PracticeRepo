package com.practice.repo;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static org.openqa.selenium.Platform.ANDROID;

import com.codeborne.selenide.logevents.SelenideLogger;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import io.qameta.allure.selenide.AllureSelenide;

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
        if (hasWebDriverStarted()) {
            Platform platform = ((RemoteWebDriver) getWebDriver()).getCapabilities().getPlatformName();
            if (!platform.is(ANDROID)) closeWebDriver();
        }
    }

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount < 2) {
            retryCount++;
            return true;
        } else return false;
    }
}