package com.practice.repo.components.web.tutorialsPoint;

import static com.codeborne.selenide.Selenide.$x;
import static com.practice.repo.enums.ResourceType.WEB;

import com.practice.repo.BaseComponent;
import com.practice.repo.utils.Resource;
import org.springframework.stereotype.Component;

import io.qameta.allure.Step;

@Component
@Resource(path = "/register.php", type = WEB)
public class TutorialsPointRegisterUser extends BaseComponent {

    private static final String FIRST_NAME_XPATH = "//input[@id='firstname']";
    private static final String LSAT_NAME_XPATH = "//input[@id='lastname']";
    private static final String USERNAME_XPATH = "//input[@id='username']";
    private static final String PASSWORD_XPATH = "//input[@id='password']";
    private static final String REGISTER_XPATH = "//input[@type='submit']";

    @Step
    public TutorialsPointRegisterUser registerUser(String firstName, String lastname,
                                                   String username, String password) {
        $x(FIRST_NAME_XPATH).setValue(firstName);
        $x(LSAT_NAME_XPATH).setValue(lastname);
        $x(USERNAME_XPATH).setValue(username);
        $x(PASSWORD_XPATH).setValue(password);
        $x(REGISTER_XPATH).click();
        return this;
    }

}
