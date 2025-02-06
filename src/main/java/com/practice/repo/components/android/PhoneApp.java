package com.practice.repo.components.android;

import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.ANDROID;

import com.google.common.primitives.Chars;
import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;

import org.springframework.stereotype.Component;

import io.qameta.allure.Step;

@Component
@Resource(type = ANDROID, path = "/")
public class PhoneApp extends BaseComponent {

    private static final String DIAL_PAD = "//android.widget.ImageButton[@content-desc='dial pad']";
    private static final String DIGITS = "//android.widget.FrameLayout[contains(@content-desc,'%s')]";

    @Step
    public PhoneApp dialNumber(String number) {
        $x(DIAL_PAD).click();
        Chars.asList(number.toCharArray()).forEach(digit -> $x(String.format(DIGITS, digit)).click());
        return this;
    }

}
