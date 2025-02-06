package com.practice.repo.glues;

import com.practice.repo.BaseTest;
import com.practice.repo.components.fakeRestApi.FakeRESTApi;
import com.practice.repo.components.tutorialsPoint.TutorialsPointRegisterUser;

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

    @When("User enters first and last names")
    public void userEntersAnd(DataTable dataTable) {
        Map<String, String> name = dataTable.asMap();
        componentManager.getComponent(TutorialsPointRegisterUser.class)
                .registerUser(name.get("first"), name.get("last"));
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
}
