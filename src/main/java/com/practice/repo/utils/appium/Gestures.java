package com.practice.repo.utils.appium;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.springframework.stereotype.Component;

import java.util.Map;

import io.appium.java_client.android.AndroidDriver;

@Component
public class Gestures {

    public void scrollIntoView(String visibleText, String direction){
        Map<String, String> towards = Map.of(
                "strategy", "-android uiautomator",
                "selector", String.format("new UiSelector().text(\"%s\")", visibleText),
                "direction", direction);
        ((AndroidDriver) getWebDriver()).executeScript("mobile: scroll", towards);
    }

}
