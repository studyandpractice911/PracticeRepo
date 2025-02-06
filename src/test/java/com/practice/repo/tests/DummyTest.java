package com.practice.repo.tests;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import com.practice.repo.BaseTest;
import com.practice.repo.components.appium.AndroidLauncher;
import com.practice.repo.components.fakeRestApi.FakeRESTApi;
import com.practice.repo.components.tutorialsPoint.TutorialsPointAlerts;
import com.practice.repo.components.tutorialsPoint.TutorialsPointRegisterUser;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DummyTest extends BaseTest {

    @Value("${book.id}")
    protected String BOOK_ID;
    @Value("${first.name}")
    protected String FIRST_NAME;
    @Value("${last.name}")
    protected String LAST_NAME;

    @Test
    public void fibbonacci() {
        Stream.iterate(List.of(0, 1), f -> List.of(f.getLast(), f.getFirst() + f.getLast()))
                .limit(20)
                .map(List::getFirst)
                .forEach(System.out::println);
    }

    @Test
    public void prime() {
        IntStream.rangeClosed(0, 20)
                .filter(dividend -> dividend > 1)
                .filter(dividend -> IntStream.range(2, dividend).noneMatch(divisor -> dividend % divisor == 0))
                .forEach(System.out::println);
    }

    @Test
    public void factorial() {
        int factorial = IntStream.rangeClosed(1, 5).reduce(1, (a, b) -> a * b);
        System.out.println(factorial);
    }

    @Test(retryAnalyzer = BaseTest.class)
    public void testA() {
        componentManager.getComponent(TutorialsPointAlerts.class).checkAlert();
        componentManager.getComponent(TutorialsPointRegisterUser.class).registerUser(FIRST_NAME, LAST_NAME);
    }

    @Test
    public void testB() {
        componentManager.getComponent(FakeRESTApi.class).getBook(BOOK_ID);
    }

    @Test
    public void callTest() {
        componentManager
                .getComponent(AndroidLauncher.class)
                .openPhone()
                .dialNumber("0123456789");
    }

    @Test
    public void googleAppTest() {
    }

    @Test
    public void testC1() {
        Response response = RestAssured.given().log().all()
                .baseUri("https://petstore.swagger.io/v2")
                .pathParam("petId", 8734534)
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("additionalMetadata", "bkjyhg")
                .multiPart("file", new File("C:/Users/Home/Downloads/image-103.png"))
                .post("/pet/{petId}/uploadImage");
        response.then().assertThat().statusCode(200).log().all();
    }

    @Test
    public void testC() {
        new FluentWait<>(getWebDriver())
                .pollingEvery(Duration.ofSeconds(2))
                .withTimeout(Duration.ofMinutes(10))
                .ignoring(NoSuchElementException.class)
                .until(isConditionMet());
    }

    @Step
    public Function<WebDriver, Boolean> isConditionMet() {
        System.out.println("DummyTest.isConditionMet");
        return new Function<>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                System.out.println("DummyTest.apply");
                return webDriver.findElement(By.xpath("//td")).isDisplayed();
            }
        };
    }
}
