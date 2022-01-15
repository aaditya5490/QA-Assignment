package com.automation.VueJS.steps;


import com.automation.VueJS.pages.ToDoPage;
import com.automation.utils.LocatorActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToDoSteps {

    private final Logger LOGGER = LoggerFactory.getLogger(ToDoSteps.class);

    @Autowired
    private ToDoPage toDoPage;

    @Autowired
    private LocatorActions locatorActions;

    public void NavigateToDoPage() {
        toDoPage.navigatetoToDOPageImplementation();
        toDoPage.verifyHomePageNavigation();
    }

    public void UserEnterTask(String string) {
        toDoPage.user_enter_task(string);

    }

    public void validateTaskinTab(String string, String string2) {
        toDoPage.validate_entered_task_appears_in_tab(string,string2);
    }

    public void validateCountofTotalTasks(String string) {
        toDoPage.validateTotalTasks(string);
    }

    public void clearTask(String string) {
        toDoPage.clearAddedTask(string);
    }

    public void markTaskComplete(String string) {
        toDoPage.makeTaskComplete(string);
    }

    public void markAllTaskComplete() {
        toDoPage.makeAllTaskComplete();
    }

    public void switchtoCompleteTab(String string) {
        toDoPage.switchTabs(string);

    }

    public void ClearCompleteJob() {
        toDoPage.clearComplete();
    }

    public void checklist() {
        toDoPage.checklistcomponents();
    }

    public void validateHomePageComponents() {
        toDoPage.validateHomePageComponents();
    }

    public void RefreshPage() {
        toDoPage.userRefreshPage();
    }

    public void openNewTab() {
        toDoPage.usrOpenNewTab();
    }
}
