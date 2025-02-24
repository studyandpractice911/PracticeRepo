package com.practice.repo.components.android;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.App.CHROME;
import static com.practice.repo.enums.App.GOOGLE;
import static com.practice.repo.enums.App.PHONE;
import static com.practice.repo.enums.ResourceType.ANDROID;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;

import com.practice.repo.BaseComponent;
import com.practice.repo.enums.App;
import com.practice.repo.utils.AutomationCapabilities;
import com.practice.repo.utils.Resource;
import com.practice.repo.utils.appium.Gestures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.qameta.allure.Step;

@Component
@Resource(type = ANDROID, path = "/")
public class AndroidLauncher extends BaseComponent {

    private static final String APPS_DRAWER = "//android.widget.TextView[@content-desc='Apps']";
    private static final String HOME_SCREEN = "//android.view.View[@content-desc='Home']";
    private static final String APP = "//android.widget.TextView[@content-desc='%s']";

    @Autowired
    AutomationCapabilities capabilities;
    @Autowired
    Gestures gestures;
    @Autowired
    Chrome chrome;
    @Autowired
    Phone phone;
    @Autowired
    Google google;

    @Step
    public AndroidLauncher openAppsDrawer() {
        if(!$x(APPS_DRAWER).is(visible)) gestures.swipeGesture($x(HOME_SCREEN), "up", 0.5F);
        else $x(APPS_DRAWER).click();
        return this;
    }

    @Step
    public AndroidLauncher checkWidgets(String appName) {
        gestures.longClickGesture($x(String.format(APP, appName)));
        return this;
    }

    @Step
    private void openApp(App app) {
        $x(String.format(APP, app.getAppName())).shouldBe(clickable).click();
    }

    @Step
    public Chrome chromeApp() {
        capabilities.setCapability(BROWSER_NAME, "Chrome");
        openApp(CHROME);
        return chrome;
    }

    @Step
    public Phone phoneApp() {
        openApp(PHONE);
        return phone;
    }

    @Step
    public Google googleApp() {
        openApp(GOOGLE);
        return google;
    }

}
