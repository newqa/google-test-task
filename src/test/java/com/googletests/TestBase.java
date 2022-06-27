package com.googletests;

import configuration.DriverManager;
import configuration.DriverUtils;
import configuration.Settings;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static Settings settings = ConfigFactory.create(Settings.class, System.getProperties());

    @BeforeMethod(alwaysRun = true)
    public void prepareTest() {
        DriverUtils.setInitialConfiguration();
        DriverUtils.navigateToPage(settings.getGoogleUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        DriverManager.quitDriver();
    }

}
