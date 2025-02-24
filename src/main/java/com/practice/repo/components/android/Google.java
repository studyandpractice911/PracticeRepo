package com.practice.repo.components.android;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.ANDROID;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;
import com.practice.repo.utils.WebDriverHandler;
import com.practice.repo.utils.appium.Gestures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;

@Component
@Resource(type = ANDROID, path = "/")
public class Google extends BaseComponent {

    private static final String SEARCH_BOX = "//com.google.android.apps.gsa.searchplate.SearchPlate[contains(@resource-id,'googlequicksearchbox')]";
    private static final String SEARCH_BOX_INPUT = "//android.widget.EditText[contains(@resource-id,'googlequicksearchbox')]";
    private static final String SEARCH_RESULT = "//android.widget.TextView[@text='IMAGES']";
    private static final String PHONE_TAB = "//android.widget.TextView[@text='PHONE']";
    @Autowired
    Gestures gestures;
    @Autowired
    WebDriverHandler webDriverHandler;

    @Step
    public Google search(String text) {
        $x(SEARCH_BOX).shouldBe(clickable).click();
        $x(SEARCH_BOX_INPUT).shouldBe(clickable).sendKeys(text);
        webDriverHandler.getAndroidDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
        $x(SEARCH_RESULT).shouldBe(visible);
        return this;
    }

    @Step
    public Google choosePhoneTabFromResult() {
        gestures.scroll("PHONE", "right");
        $x(PHONE_TAB).click();
        return this;
    }

}
