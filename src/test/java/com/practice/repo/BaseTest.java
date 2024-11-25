package com.practice.repo;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.practice.repo.utils.Resource;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;
import static com.practice.repo.enums.ResourceType.UI;

public class BaseTest extends SpringTestConfiguration {

    @Autowired
    BaseComponent baseComponent;

    protected <T> T getComponent(Class<T> component) {
        if(!component.getPackageName().contains("com.practice.repo.components")){
            throw new RuntimeException("getComponent() can only be used to access beans from package : com.practice.repo.components");
        }
        assert applicationContext != null;
        T bean = applicationContext.getBean(component);
        String packageName = new String();
        String resourcePath = new String();
        boolean shouldLaunchDriver = false;
        try {
            Resource resource = bean.getClass().getAnnotation(Resource.class);
            if(resource.type().equals(UI)){
                packageName = Arrays.stream(bean.getClass().getPackageName().split("\\.")).toList().getLast();
                resourcePath = bean.getClass().getAnnotation(Resource.class).path();
                shouldLaunchDriver=true;
            }
        } catch (NullPointerException e){
            throw new NullPointerException("Please define @Resource annotation in class : " + bean.getClass().getCanonicalName());
        }
        if(shouldLaunchDriver) {
            baseComponent.launchDriver(packageName, resourcePath);
        }
        return bean;
    }

    @BeforeSuite
    public void prepareSuite() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterMethod
    public void closeDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            closeWebDriver();
        }
    }
}