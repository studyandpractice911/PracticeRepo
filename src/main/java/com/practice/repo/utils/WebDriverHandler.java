package com.practice.repo.utils;

import static com.practice.repo.enums.UserAgent.MOBILE;
import static com.practice.repo.enums.UserAgent.TABLET;

import com.codeborne.selenide.WebDriverRunner;
import com.practice.repo.enums.UserAgent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

@Component
public class WebDriverHandler {

    @Autowired
    AutomationCapabilities capabilities;

    @Autowired
    Environment environment;

    public WebDriver configureWebDriver(Resource resource) {
        UserAgent userAgent = resource.userAgent();
        if(userAgent==MOBILE || userAgent==TABLET)
            throw new RuntimeException("Can not access " +
                    String.join(" ", userAgent.name(), resource.type().name()) +
                    " component directly");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(List.of(
                "--start-maximized",
                "--disable-notifications",
                "--ignore-certificate-errors"
        ));
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        return driver;
    }

    public WebDriver configureAndroidDriver() throws MalformedURLException {
        capabilities.setCapability("appium:platformName", environment.getProperty("appium.platform.name"));
        capabilities.setCapability("appium:platformVersion", environment.getProperty("appium.platform.version"));
        capabilities.setCapability("appium:deviceName", environment.getProperty("appium.device.name"));
        capabilities.setCapability("appium:automationName", environment.getProperty("appium.automation.name"));
        WebDriver driver = new AndroidDriver(new URL(System.getProperty("base.url")), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
        return driver;
    }

    public WebDriver getWebDriver(){
        return WebDriverRunner.getWebDriver();
    }

    public RemoteWebDriver getRemoteWebDriver(){
        return (RemoteWebDriver) WebDriverRunner.getWebDriver();
    }

    public AndroidDriver getAndroidDriver(){
        return (AndroidDriver) WebDriverRunner.getWebDriver();
    }

    public JavascriptExecutor getJavascriptExecutor(){
        return (JavascriptExecutor) WebDriverRunner.getWebDriver();
    }

}
