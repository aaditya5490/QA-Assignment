package com.automation.VueJS.suite;


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

}