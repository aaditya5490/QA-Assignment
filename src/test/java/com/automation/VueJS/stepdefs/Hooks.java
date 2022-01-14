package com.automation.VueJS.stepdefs;

import com.automation.config.BrowserLib;
import com.automation.utils.FileDirUtils;
import com.automation.utils.LoggerUtil;
import com.automation.utils.ScenarioUtils;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class Hooks implements En {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);
    public static WebDriver driver;
    public static final String SCREENSHOT_PATH = "target/cucumber-advanced-reports/screenshots";


    @Autowired
    private BrowserLib browserLib;

    @Autowired
    private ScenarioUtils scenarioUtils;

    @Autowired
    private LocatorActions locatorActions;

    @Autowired
    private FileDirUtils fileDirUtils;

    @Autowired
    private LoggerUtil loggerUtil;

    public Hooks() {
        Before((Scenario scenario) -> {
            scenarioUtils.setScenario(scenario);
            LOGGER.info("-------Session Started-------");
        });

        After(() -> {
            if (scenarioUtils.isScenarioFailed()) {
                LOGGER.info("Scenario Failed {}", scenarioUtils.getScenarioName());
            }
            locatorActions.takeScreenshot("screenshot");
            LOGGER.info("-------Screen shot added-------");
        });
    }

}