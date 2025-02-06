package com.practice.repo;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;

public class BaseTest extends SpringTestConfiguration implements IRetryAnalyzer {

    @Autowired
    protected ComponentManager componentManager;
    protected int retryCount = 0;
    private static final String CLEAR_ALL = "//android.widget.TextView[@text='CLEAR ALL']";
    private static final String DISMISS_CURRENT = "//android.widget.ImageView[contains(@content-desc,'Dismiss')]";

    @Step
    public void clearRecentAppsOnAndroid() {
        AndroidDriver androidDriver = (AndroidDriver) getWebDriver();
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
        if (hasWebDriverStarted()){
            Platform platform = ((RemoteWebDriver) getWebDriver()).getCapabilities().getPlatformName();
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