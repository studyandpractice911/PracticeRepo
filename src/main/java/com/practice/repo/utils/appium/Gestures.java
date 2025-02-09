package com.practice.repo.utils.appium;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import com.practice.repo.utils.WebDriverHandler;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import io.appium.java_client.android.AndroidDriver;

@Component
public class Gestures {

    @Autowired
    WebDriverHandler webDriverHandler;

    /**
     * @param direction up, down, left, right
     */
    public void scroll(String visibleText, String direction){
        Map<String, String> info = Map.of(
                "strategy", "-android uiautomator",
                "selector", String.format("new UiSelector().text(\"%s\")", visibleText),
                "direction", direction);
        webDriverHandler.getAndroidDriver().executeScript("mobile: scroll", info);
    }

    /**
     * @param direction up, down, left, right
     * @param percent 0.0F to 1.0F
     */
    public void swipeGesture(WebElement webElement, String direction, Float percent){
        Map<String, Object> info = Map.of(
                "element", webElement,
                "direction", direction,
                "percent", percent);
        webDriverHandler.getAndroidDriver().executeScript("mobile: swipeGesture", info);
    }

    public void longClickGesture(WebElement webElement){
        Map<String, Object> info = Map.of("element", webElement);
        webDriverHandler.getAndroidDriver().executeScript("mobile: longClickGesture", info);
    }

}
