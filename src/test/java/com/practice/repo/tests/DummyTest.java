package com.practice.repo.tests;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.practice.repo.BaseTest;
import com.practice.repo.components.android.AndroidLauncher;
import com.practice.repo.components.android.Chrome;
import com.practice.repo.components.api.fakeRestApi.FakeRESTApi;
import com.practice.repo.components.web.amazonIndia.AmazonIndia;
import com.practice.repo.components.web.tutorialsPoint.TutorialsPointAlerts;
import com.practice.repo.components.web.tutorialsPoint.TutorialsPointRegisterUser;

import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

public class DummyTest extends BaseTest {

    @Value("${book.id}")
    protected String BOOK_ID;
    @Value("${first.name}")
    protected String FIRST_NAME;
    @Value("${last.name}")
    protected String LAST_NAME;

    @Test(retryAnalyzer = BaseTest.class)
    public void seleniumTest() {
        componentManager.getComponent(TutorialsPointAlerts.class).checkAlert();
        closeWebDriver();
        componentManager.getComponent(TutorialsPointRegisterUser.class).registerUser(FIRST_NAME, LAST_NAME);
    }

    @Test
    public void restAssuredTest() {
        componentManager.getComponent(FakeRESTApi.class).getBook(BOOK_ID);
    }

    @Test(description = "appiumTest")
    public void callTest() {
        componentManager
                .getComponent(AndroidLauncher.class)
                .openAppsDrawer()
                .phoneApp()
                .dialNumber("5312649870");
    }

    @Test(description = "appiumTest")
    public void googleAppSearchTest() {
        componentManager
                .getComponent(AndroidLauncher.class)
                .openAppsDrawer()
                .googleApp()
                .search("Appium")
                .choosePhoneTabFromResult();
    }

    @Test(description = "appiumTest")
    public void chromeMobileAppTest() {
        componentManager
                .getComponent(AndroidLauncher.class)
                .openAppsDrawer()
                .chromeApp();
        componentManager
                .getComponent(AmazonIndia.class)
                .checkDeals();
        componentManager
                .getComponent(Chrome.class)
                .closeTabs();
    }

    @Test(description = "appiumTest")
    public void anotherAppiumTest() {
        componentManager
                .getComponent(AndroidLauncher.class)
                .openAppsDrawer()
                .checkWidgets("Google");
    }
}
