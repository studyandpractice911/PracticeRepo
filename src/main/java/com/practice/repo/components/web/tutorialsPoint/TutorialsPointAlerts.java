package com.practice.repo.components.web.tutorialsPoint;

import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.WEB;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;
import com.practice.repo.utils.WebDriverHandler;

import org.openqa.selenium.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.qameta.allure.Step;

@Component
@Resource(path = "/alerts.php", type = WEB)
public class TutorialsPointAlerts extends BaseComponent {

    private static final String ALERT_XPATH = "//button[text()='Alert']";
    @Autowired
    WebDriverHandler webDriverHandler;

    @Step
    public TutorialsPointAlerts checkAlert() {
        $x(ALERT_XPATH).click();
        Alert alert = webDriverHandler.getWebDriver().switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        return this;
    }

}
