package com.automation.VueJS.suite;


import com.automation.config.BrowserLib;
import com.automation.svc.MasterthoughtReportsSvc;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.automation.VueJS.stepdefs"},
        features = {"src/test/resources/features"},
        plugin = {"pretty", "json:target/cucumber-advanced-reports/cucumber.json"},
        monochrome = true,
        strict = true,
        tags = "@p1_test")
public class RunWIPCukes {
    @BeforeClass
    public static void launchDriver() {
        BrowserLib.launchDriver();
    }

    @AfterClass
    public static void tearDown() {
        BrowserLib.quitDriver();
        MasterthoughtReportsSvc.generateReport();

}