package com.practice.repo;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static org.openqa.selenium.Platform.ANDROID;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.practice.repo.utils.WebDriverHandler;

import org.openqa.selenium.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;

public class BaseTest extends SpringTestConfiguration implements IRetryAnalyzer {

    private static final String CLEAR_ALL = "//android.widget.TextView[@text='CLEAR ALL']";
    private static final String DISMISS_CURRENT = "//android.widget.ImageView[contains(@content-desc,'Dismiss')]";
    @Autowired
    protected ComponentManager componentManager;
    @Autowired
    WebDriverHandler webDriverHandler;
    private int retryCount = 0;

    @Step
    public void clearRecentAppsOnAndroid() {
        AndroidDriver androidDriver = webDriverHandler.getAndroidDriver();
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
        androidDriver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
        if (!$x(CLEAR_ALL).is(visible)) $$x(DISMISS_CURRENT)
                .forEach(app -> $x(DISMISS_CURRENT).shouldBe(visible).click());
        else $x(CLEAR_ALL).click();
    }

    @BeforeSuite
    public void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterMethod
    public void closeDriver() {
        if (hasWebDriverStarted()) {
            Platform platform = webDriverHandler.getRemoteWebDriver().getCapabilities().getPlatformName();
            if (platform.is(ANDROID)) clearRecentAppsOnAndroid();
            closeWebDriver();
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