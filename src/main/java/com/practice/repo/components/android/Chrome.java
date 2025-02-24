package com.practice.repo.components.android;

import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.ANDROID;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;

import org.springframework.stereotype.Component;

import io.qameta.allure.Step;

@Component
@Resource(type = ANDROID, path = "/")
public class Chrome extends BaseComponent {

    private static final String TAB_SWITCHER = "//android.widget.ImageButton[contains(@resource-id,'tab_switcher_button')]";
    private static final String MENU = "//android.widget.ImageButton[contains(@resource-id,'menu_button')]";
    private static final String CLOSE_ALL_TABS = "//android.widget.TextView[@text='Close all tabs']";

    @Step
    public Chrome closeTabs() {
        $x(TAB_SWITCHER).click();
        $x(MENU).click();
        $x(CLOSE_ALL_TABS).click();
        return this;
    }

}
