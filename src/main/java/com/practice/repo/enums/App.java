package com.practice.repo.enums;

public enum App {

    CHROME("Chrome"),
    GOOGLE("Google"),
    PHONE("Phone");

    private final String appName;

    App(String appName) {
        this.appName=appName;
    }

    public String getAppName() {
        return appName;
    }
}