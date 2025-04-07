package com.practice.repo.glues;

import com.practice.repo.BaseTest;
import com.practice.repo.components.api.fakeRestApi.FakeRESTApi;
import com.practice.repo.components.web.tutorialsPoint.TutorialsPointRegisterUser;

import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DummyStepDefinitions extends BaseTest {

    //TODO - More configuration required

    @Given("User opens tutorials point webpage")
    public void userOpensTutorialsPointWebpage() {
        componentManager.getComponent(TutorialsPointRegisterUser.class);
    }

    @When("User fills the form")
    public void userEntersAnd(DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps();
        users.forEach(user -> {
            componentManager.getComponent(TutorialsPointRegisterUser.class)
                    .registerUser(user.get("firstName"), user.get("lastName"),
                            user.get("username"), user.get("password"));
        });
    }

    @Then("User clicks on register button")
    public void userClicksOnRegisterButton() {
        System.out.println("DummyStepDefinitions.userClicksOnRegisterButton");
    }

    @Given("User invokes a rest call")
    public void userInvokesARestCall() {
        System.out.println("DummyStepDefinitions.userInvokesARestCall");
    }

    @When("User performs GET call for {string}")
    public void userPerformsGETCallFor(String arg0) {
        componentManager.getComponent(FakeRESTApi.class).getBook(arg0);
    }

    @Then("User gets the book details")
    public void userGetsTheBookDetails() {
        System.out.println("DummyStepDefinitions.userGetsTheBookDetails");
    }

    @When("User enters {string}, {string}, {string}, {string}")
    public void userEnters(String firstName, String lastName, String username, String password) {
        componentManager.getComponent(TutorialsPointRegisterUser.class)
                .registerUser(firstName, lastName, username, password);
    }
}
