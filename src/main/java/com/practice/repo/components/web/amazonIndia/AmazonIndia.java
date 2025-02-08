package com.practice.repo.components.web.amazonIndia;

import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.WEB;
import static com.practice.repo.enums.UserAgent.MOBILE;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;

import org.springframework.stereotype.Component;

import io.qameta.allure.Step;

@Component
@Resource(type = WEB, userAgent = MOBILE, path = "/")
public class AmazonIndia extends BaseComponent {

    private static final String DEALS = "//a[text()='Deals']";

    @Step
    public AmazonIndia checkDeals() {
        $x(DEALS).click();
        return this;
    }

}
