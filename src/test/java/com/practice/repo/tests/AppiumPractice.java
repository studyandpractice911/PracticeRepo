package com.practice.repo.tests;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class AppiumPractice {

    AndroidDriver androidDriver;
    Duration timeout = Duration.ofSeconds(10L);

    @BeforeTest(alwaysRun = true)
    public void setupAndroidDriverWithCapabilities() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "ANDROID");
        capabilities.setCapability("appium:platformVersion", "7");
        capabilities.setCapability("appium:deviceName", "myAndroidVM");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        androidDriver.manage().timeouts().implicitlyWait(timeout);
    }

    @AfterTest(alwaysRun = true)
    public void teardown() {
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
        androidDriver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='CLEAR ALL']")).click();
    }

    @Test
    public void callTest() {
        androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc='Apps']")).click();
        new WebDriverWait(androidDriver, timeout)
                .until(elementToBeClickable(By.xpath("//android.widget.TextView[@content-desc='Phone']")));
        androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc='Phone']")).click();
        androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='dial pad']")).click();
        IntStream.rangeClosed(0, 9)
                .forEach(num -> androidDriver.findElement(By.xpath(String.format("//android.widget.FrameLayout[contains(@content-desc,'%s')]", num))).click());
        String dialedNumber = androidDriver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'digits')]")).getAttribute("text");
        assert dialedNumber != null;
        dialedNumber = dialedNumber.replaceAll("[^0-9]", "");
        Assert.assertEquals(dialedNumber, "0123456789");
    }

    @Test
    public void scrollTest() {
        androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc='Apps']")).click();
        new WebDriverWait(androidDriver, timeout)
                .until(elementToBeClickable(By.xpath("//android.widget.TextView[@content-desc='Google']")));
        androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc='Google']")).click();
        new WebDriverWait(androidDriver, timeout)
                .until(elementToBeClickable(By.xpath("//com.google.android.apps.gsa.searchplate.SearchPlate[contains(@resource-id,'googlequicksearchbox')]")));
        androidDriver.findElement(By.xpath("//com.google.android.apps.gsa.searchplate.SearchPlate[contains(@resource-id,'googlequicksearchbox')]")).click();
        new WebDriverWait(androidDriver, timeout)
                .until(elementToBeClickable(By.xpath("//android.widget.EditText[contains(@resource-id,'googlequicksearchbox')]")));
        androidDriver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'googlequicksearchbox')]")).sendKeys("appium");
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
        new WebDriverWait(androidDriver, timeout)
                .until(visibilityOf(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='IMAGES']"))));
        Map<String, String> upto = Map.of(
                "strategy", "-android uiautomator",
                "selector", "new UiSelector().text(\"Settings\")",
                "direction", "down"
        );
        androidDriver.findElement(By.xpath("//android.view.View[@content-desc='People also ask']")).click();
        androidDriver.executeScript("mobile: scroll", upto);
        androidDriver.findElement(By.xpath("//android.view.View[@content-desc='Settings']")).click();
    }

}
