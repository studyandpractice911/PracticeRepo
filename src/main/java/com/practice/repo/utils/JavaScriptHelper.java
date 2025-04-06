package com.practice.repo.utils;

import org.springframework.stereotype.Component;

@Component
public class JavaScriptHelper {

    /**
     * @implNote - Ensure to pass WebElement as 2nd parameter of executeScript()
     */
    public String clickOnElement() {
        return "arguments[0].click();";
    }

    /**
     * @implNote - Ensure to pass WebElement as 2nd parameter of executeScript()
     */
    public String scrollIntoView() {
        return "arguments[0].scrollIntoView();";
    }

    /**
     * @implNote - Ensure to wrap the result of executeScript() in String
     * @implNote - Ensure to pass WebElement as 2nd parameter of executeScript()
     */
    public String getInnerText() {
        return "return arguments[0].innerText";
    }

    /**
     * @implNote - Ensure to wrap the result of executeScript() in String
     */
    public String getPageTitle() {
        return "return document.title;";
    }

    /**
     * @implNote - Ensure to wrap the result of executeScript() in String
     */
    public String getCurrentUrl() {
        return "return document.URL;";
    }

    /**
     * @implNote - Ensure to wrap the result of executeScript() in String
     */
    public String getCurrentDomain() {
        return "return document.domain;";
    }

    public String createAlert(String alertText) {
        return String.format("alert('%s');", alertText);
    }

    public String navigateToUrl(String url) {
        return String.format("window.location = '%s';", url);
    }

    public String horizontalScroll(long xAxisPixels) {
        return String.format("window.scrollBy(%s,0)", xAxisPixels);
    }

    public String verticalScroll(long yAxisPixels) {
        return String.format("window.scrollBy(0,%s)", yAxisPixels);
    }

    public String scrollToBottom() {
        return "window.scrollBy(0,document.body.scrollHeight)";
    }

    public String scrollToTop() {
        return "window.scrollTo(document.body.scrollHeight, 0)";
    }

    public String setValue(String elementId, String value) {
        return String.format("document.getElementById('%s').value='%s';", elementId, value);
    }

    public String enableCheckbox(String elementId) {
        return String.format("document.getElementById('%s').checked=true;", elementId);
    }

}