package com.automation.VueJS.stepdefs;

import com.automation.VueJS.pages.ToDoPage;
import com.automation.VueJS.steps.ToDoSteps;
import io.cucumber.java8.En;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ToDoStepDef implements En {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoStepDef.class);


    @Autowired
    private ToDoSteps toDoSteps;

    @Autowired
    private ToDoPage toDoPage;

    public ToDoStepDef() {
        Given("User visit ToDo mvc Page", () -> {
            LOGGER.info("Step Definition ----- User is Navigated to ToDo Page");
            toDoSteps.NavigateToDoPage();
        });

        Given("User is able to see Components Loaded in the Page", () -> {
            LOGGER.info("Step Definition ----- User is able to see Components Loaded in the Page");
            toDoSteps.validateHomePageComponents();
        });

        When("User enters the task {string} in ToDo Box", (String string) -> {
            LOGGER.info("Step Definition ----- User enters the task" + string + "in ToDo Box");
            toDoSteps.UserEnterTask(string);

        });

        When("Added Task {string} appears in {string} Tab", (String string, String string2) -> {
            LOGGER.info("Step Definition ----- Added Task" + string + "appears in"+ string2 + "Tab");
            toDoSteps.validateTaskinTab(string,string2);
        });

        Then("User able to see total Items left as {string}", (String string) -> {
            toDoSteps.validateCountofTotalTasks(string);
        });

        Then("User Clears Added Task {string}", (String string) -> {
            toDoSteps.clearTask(string);
        });

        When("User marks added task {string} as complete", (String string) -> {
            toDoSteps.markTaskComplete(string);
        });

        When("User marks added task {string} as incomplete", (String string) -> {
            toDoSteps.markTaskIncomplete(string);
        });

        When("User does Select All task in the list to mark complete", () -> {
            toDoSteps.markAllTaskInComplete();
        });

        When("User does Select All task in the list to mark Incomplete", () -> {
            toDoSteps.markAllTaskComplete();
        });

        When("Switch to {string} Tab", (String string) -> {
            toDoSteps.switchtoCompleteTab(string);
        });

        Then("User is able to Clear all completed jobs", () -> {
            toDoSteps.ClearCompleteJob();
        });

        Then("ToDo List is Empty", () -> {
            toDoSteps.checklist();
        });

        Then("User Refresh Page", () -> {
            toDoSteps.RefreshPage();
        });

        When("User opens new Tab", () -> {
            toDoSteps.openNewTab();
        });

    }
}
