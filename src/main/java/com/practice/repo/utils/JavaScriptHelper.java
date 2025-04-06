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

    public String createAlert(String alertText) {
        return String.format("alert('%s');", alertText);
    }

    public String navigateToUrl(String url) {
        return String.format("window.location = '%s';", url);
    }

    public String horizontalScroll(long xAxisPixels) {
        return String.format("window.scrollBy(%s,0)", xAxisPixels);
    }

    public String setValue(String elementId, String value) {
        return String.format("document.getElementById('%s').value='%s';", elementId, value);
    }

    public String enableCheckbox(String elementId) {
        return String.format("document.getElementById('%s').checked=true;", elementId);
    }

    public String getInnerText() {
        return "return document.documentElement.innerText;";
    }

    public String getPageTitle() {
        return "return document.title;";
    }

}