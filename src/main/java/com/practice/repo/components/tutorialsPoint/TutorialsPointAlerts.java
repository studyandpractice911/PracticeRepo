package com.practice.repo.components.tutorialsPoint;

import com.codeborne.selenide.WebDriverRunner;
import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.UI;

@Component
@Resource(path = "/alerts.php", type = UI)
public class TutorialsPointAlerts extends BaseComponent {

    private static final String ALERT_XPATH = "//button[text()='Alert']";

    @Step
    public TutorialsPointAlerts checkAlert() {
        $x(ALERT_XPATH).click();
        Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        return this;
    }

}
