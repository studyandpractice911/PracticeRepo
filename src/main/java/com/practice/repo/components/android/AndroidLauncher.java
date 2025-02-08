package com.practice.repo.components.android;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.ANDROID;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.AutomationCapabilities;
import com.practice.repo.utils.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.qameta.allure.Step;

@Component
@Resource(type = ANDROID, path = "/")
public class AndroidLauncher extends BaseComponent {

    private static final String APPS_DRAWER = "//android.widget.TextView[@content-desc='Apps']";
    private static final String PHONE_APP = "//android.widget.TextView[@content-desc='Phone']";
    private static final String GOOGLE_APP = "//android.widget.TextView[@content-desc='Google']";
    private static final String CHROME_APP = "//android.widget.TextView[@content-desc='Chrome']";

    @Autowired
    PhoneApp phoneApp;
    @Autowired
    GoogleApp googleApp;
    @Autowired
    ChromeApp chromeApp;
    @Autowired
    AutomationCapabilities capabilities;

    @Step
    public AndroidLauncher openAppsDrawer() {
        $x(APPS_DRAWER).click();
        return this;
    }

    @Step
    public PhoneApp phoneApp() {
        $x(PHONE_APP).shouldBe(clickable).click();
        return phoneApp;
    }

    @Step
    public GoogleApp googleApp() {
        $x(GOOGLE_APP).shouldBe(clickable).click();
        return googleApp;
    }

    @Step
    public ChromeApp chromeApp() {
        capabilities.setCapability(BROWSER_NAME, "Chrome");
        $x(CHROME_APP).shouldBe(clickable).click();
        return chromeApp;
    }

}
