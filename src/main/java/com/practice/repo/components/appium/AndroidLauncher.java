package com.practice.repo.components.appium;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.ANDROID;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.qameta.allure.Step;

@Component
@Resource(type = ANDROID, path = "/")
public class AndroidLauncher extends BaseComponent {

    @Autowired
    Phone phone;

    private static final String APPS_XPATH = "//android.widget.TextView[@content-desc='Apps']";
    private static final String PHONE_XPATH = "//android.widget.TextView[@content-desc='Phone']";

    @Step
    public Phone openPhone() {
        $x(APPS_XPATH).click();
        $x(PHONE_XPATH).shouldBe(clickable).click();
        return phone;
    }

}
