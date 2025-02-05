package com.practice.repo.tests;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.practice.repo.BaseTest;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.stream.IntStream;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AutomationName;

public class AppiumPractice extends BaseTest {

    @BeforeTest(alwaysRun = true)
    public void setupAndroidDriverWithCapabilities() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", Platform.ANDROID.name());
        capabilities.setCapability("appium:platformVersion", "7");
        capabilities.setCapability("appium:deviceName", "myAndroidVM");
        capabilities.setCapability("appium:automationName", AutomationName.ANDROID_UIAUTOMATOR2);

        WebDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterTest(alwaysRun = true)
    public void teardown() {
        AndroidDriver androidDriver = (AndroidDriver) getWebDriver();
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
        androidDriver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
        String dismissXpath = "//android.widget.ImageView[contains(@content-desc,'Dismiss')]";
        String clearAll = "//android.widget.TextView[@text='CLEAR ALL']";
        SelenideElement dismiss = $x(dismissXpath);
        if (!$x(clearAll).is(visible)) $$x(dismissXpath).forEach(app -> dismiss.click());
        else $x(clearAll).click();
    }

    @Test
    public void callTest() {
        $x("//android.widget.TextView[@content-desc='Apps']").click();
        $x("//android.widget.TextView[@content-desc='Phone']").shouldBe(clickable).click();
        $x("//android.widget.ImageButton[@content-desc='dial pad']").click();
        IntStream.rangeClosed(0, 9).forEach(
                num -> $x(String.format("//android.widget.FrameLayout[contains(@content-desc,'%s')]", num)).click());
        $x("//android.widget.EditText[contains(@resource-id,'digits')]").shouldHave(attribute("text", "0123456789"));
    }

    @Test
    public void scrollTest() {
        AndroidDriver androidDriver = (AndroidDriver) getWebDriver();
        $x("//android.widget.TextView[@content-desc='Apps']").click();
        $x("//android.widget.TextView[@content-desc='Google']").shouldBe(clickable).click();
        $x("//com.google.android.apps.gsa.searchplate.SearchPlate[contains(@resource-id,'googlequicksearchbox')]").shouldBe(clickable).click();
        $x("//android.widget.EditText[contains(@resource-id,'googlequicksearchbox')]").shouldBe(clickable).sendKeys("appium");
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
        $x("//android.widget.TextView[@text='IMAGES']").shouldBe(visible);
        Map<String, String> scrollUntil = Map.of(
                "strategy", "-android uiautomator",
                "selector", "new UiSelector().text(\"PHONE\")",
                "direction", "down");
        androidDriver.executeScript("mobile: scroll", scrollUntil);
        $x("//android.widget.TextView[@text='PHONE']").click();
    }

}
