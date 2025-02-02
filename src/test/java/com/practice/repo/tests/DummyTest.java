package com.practice.repo.tests;

import com.practice.repo.BaseTest;
import com.practice.repo.components.fakeRestApi.FakeRESTApi;
import com.practice.repo.components.tutorialsPoint.TutorialsPointAlerts;
import com.practice.repo.components.tutorialsPoint.TutorialsPointRegisterUser;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DummyTest extends BaseTest {

    @Value("${book.id}")
    protected String BOOK_ID;
    @Value("${first.name}")
    protected String FIRST_NAME;
    @Value("${last.name}")
    protected String LAST_NAME;

    WebDriver driver;
    String NUM_6 = "//android.widget.Button[@text='6']";
    String NUM_9 = "//android.widget.Button[@text='9']";
    String MULTIPLY = "//android.widget.Button[@content-desc='multiply']";
    String RESULT = "//android.widget.TextView[@index='2']";
    String CLEAR_ALL = "//android.widget.TextView[@text='CLEAR ALL']";

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

    /**
     * adb shell dumpsys window displays - To get appPackage and appActivity
     */
    public void setupAndroidDriverWithCapabilities() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "ANDROID");
        capabilities.setCapability("appium:deviceName", "myAndroidVM");
        capabilities.setCapability("appium:platformVersion", "7");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.android.calculator2");
        capabilities.setCapability("appium:appActivity", "com.android.calculator2.Calculator");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30L));
    }

    @Test
    public void appiumTest() throws MalformedURLException {
        setupAndroidDriverWithCapabilities();
        driver.findElement(By.xpath(NUM_6)).click();
        driver.findElement(By.xpath(MULTIPLY)).click();
        driver.findElement(By.xpath(NUM_9)).click();
        String result = driver.findElement(By.xpath(RESULT)).getAttribute("text");
        Assert.assertEquals(result, "54");
        teardown();
    }

    public void teardown(){
        AndroidDriver androidDriver = (AndroidDriver) driver;
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
        androidDriver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
        androidDriver.findElement(By.xpath(CLEAR_ALL)).click();
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
