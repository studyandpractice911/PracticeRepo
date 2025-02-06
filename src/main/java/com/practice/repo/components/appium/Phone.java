package com.practice.repo.components.appium;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.ANDROID;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;

import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

import io.qameta.allure.Step;

@Component
@Resource(type = ANDROID, path = "/")
public class Phone extends BaseComponent {

    private static final String DIAL_PAD_XPATH = "//android.widget.ImageButton[@content-desc='dial pad']";
    private static final String DIGITS_XPATH = "//android.widget.FrameLayout[contains(@content-desc,'%s')]";
    private static final String NUMBER_XPATH = "//android.widget.EditText[contains(@resource-id,'digits')]";

    @Step
    public Phone dialNumber(String number) {
        $x(DIAL_PAD_XPATH).click();
        IntStream.rangeClosed(0, 9).forEach(num -> $x(String.format(DIGITS_XPATH, num)).click());
        $x(NUMBER_XPATH).shouldHave(attribute("text", number));
        return this;
    }

}
