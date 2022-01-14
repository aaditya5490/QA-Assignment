package com.automation.utils;

import io.cucumber.java8.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScenarioUtils {

    private InheritableThreadLocal<Scenario> scenario = new InheritableThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ScenarioUtils.class);


    public Scenario getScenario() {
        return scenario.get();
    }

    public void setScenario(Scenario scenario) {
        this.scenario.set(scenario);
        LOGGER.info("Set Scenario  {}",scenario.getName());
    }

    public void embed(final byte[] bytes, final String var,final String name) {
        if (getScenario() != null) {
            getScenario().attach(bytes, var, name);
        }
    }

    public boolean isScenarioFailed() {
        return this.getScenario() != null && this.getScenario().isFailed();
    }

    public String getScenarioName() {
        if (this.getScenario() != null) {
            return this.getScenario().getName();
        }
        return "";
    }


}
