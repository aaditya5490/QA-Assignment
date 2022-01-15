package com.automation.VueJS.pages;

import com.automation.config.ConfigDetails;
import com.automation.utils.LocatorActions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:locators/ToDo_page.properties")
public class ToDoPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoPage.class);
    @Autowired
    private LocatorActions locatorActions;

    @Autowired
    private Environment env;

    public void  navigatetoToDOPageImplementation() {
        locatorActions.navigateUrl(ConfigDetails.getAutUrl());
    }

    public void verifyHomePageNavigation() {
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("label.headertext"));
        locatorActions.verifyElement(by);
        //System.out.println("**********Page title********"+locatorActions.getPageTitle() );
        Assert.assertTrue(locatorActions.getPageTitle().contains("Vue.js"));
        Assert.assertTrue(locatorActions.getPageTitle().contains("TodoMVC"));
    }

    public void user_enter_task(String task) {
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("textbox.todo"));
        locatorActions.sendKeys(by,task);
        locatorActions.PressEnterKey(by);
        by = locatorActions.constructByObjectFromExpression(env.getProperty("list.todoview"));
        locatorActions.verifyElementIsVisible(by);
    }

    public void validate_entered_task_appears_in_tab(String string, String string2) {
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("list.todo")+"/li//label[text()='"+string+"']");
        if (string2.contentEquals("All"))
        {
            by = locatorActions.constructByObjectFromExpression(env.getProperty("tab.All"));
        }
        else if (string2.contentEquals("Active"))
        {
            by = locatorActions.constructByObjectFromExpression(env.getProperty("tab.Active"));
        }
        locatorActions.click(by);
        by = locatorActions.constructByObjectFromExpression(env.getProperty("list.todo")+"/li//label[text()='"+string+"']");
        locatorActions.verifyElementIsVisible(by);
        locatorActions.takeScreenshot("validatetaskinAll");

    }

    public void validateTotalTasks(String string) {
        locatorActions.setImplicitWaitTimeOutSeconds(2);
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("label.ToDoCount"));
        Assert.assertEquals(Integer.parseInt(string),Integer.parseInt(locatorActions.getLabelText(by)));
    }

    public void clearAddedTask(String string) {
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("list.todo")+"/li//label[text()='"+string+"']");
        locatorActions.click(by);
        by = locatorActions.constructByObjectFromExpression(env.getProperty("list.todo")+"/li//label[text()='"+string+"']/following-sibling::button");
        locatorActions.click(by);
        locatorActions.takeScreenshot("After First test case task clear");
    }

    public void ToggleComplete(String string)  {
        System.out.println("Xpath of the element ----"+ env.getProperty("list.todo")+"/li//label[text()='"+string+"']/preceding-sibling::input");
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("list.todo")+"/li//label[text()='"+string+"']/preceding-sibling::input");
        WebElement element = locatorActions.findElement(by);
        locatorActions.clickOnElementByJS(element);
    }

    public void makeAllTaskComplete() {
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("checkbox.toggleall"));
        locatorActions.clickIfVisible(by);
        locatorActions.takeScreenshot("After cleared task");
    }

    public void switchTabs(String string) {
        By by = null;
        if (string.contentEquals("Completed"))
        {
            by = locatorActions.constructByObjectFromExpression(env.getProperty("tab.Completed"));
        }
        else if(string.contentEquals("Active"))
        {
            by = locatorActions.constructByObjectFromExpression(env.getProperty("tab.Active"));
        }
        locatorActions.clickIfVisible(by);
    }

    public void clearComplete() {
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("button.Clearcompleted"));
        locatorActions.clickIfVisible(by);
    }

    public void checklistcomponents() {
        By by = locatorActions.constructByObjectFromExpression(env.getProperty("list.todoview"));
        locatorActions.verifyElementIsInVisible(by,5);
        locatorActions.takeScreenshot("ChecklistEmpty");

    }

    public void validateHomePageComponents() {
        By by = null ;
        boolean flag = false;
        String url = "";
                by  = locatorActions.constructByObjectFromExpression(env.getProperty("label.headertext"));
        locatorActions.verifyElementIsVisible(by);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("textbox.todo"));
        locatorActions.verifyElementIsVisible(by);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("section.footer"));
        locatorActions.verifyElementIsVisible(by);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("section.footer"));
        locatorActions.verifyElementIsVisible(by);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("lnk_source"));
         url = locatorActions.getAttribute(by, "href");
         flag = locatorActions.verifyBrokenLink(url);
        Assert.assertTrue(flag);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("lnk_Documenrtation"));
         url = locatorActions.getAttribute(by, "href");
         flag = locatorActions.verifyBrokenLink(url);
        Assert.assertTrue(flag);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("lnk_APIRef"));
        url = locatorActions.getAttribute(by, "href");
        flag = locatorActions.verifyBrokenLink(url);
        Assert.assertTrue(flag);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("lnk_VueJSGithub"));
        url = locatorActions.getAttribute(by, "href");
        flag = locatorActions.verifyBrokenLink(url);
        Assert.assertTrue(flag);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("lnk_Examples"));
        url = locatorActions.getAttribute(by, "href");
        flag = locatorActions.verifyBrokenLink(url);
        Assert.assertTrue(flag);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("lnk_CommunityTwitter"));
        url = locatorActions.getAttribute(by, "href");
        flag = locatorActions.verifyBrokenLink(url);
        Assert.assertTrue(flag);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("lnk_Gitter"));
        url = locatorActions.getAttribute(by, "href");
        flag = locatorActions.verifyBrokenLink(url);
        Assert.assertTrue(flag);

        by  = locatorActions.constructByObjectFromExpression(env.getProperty("lnk_GithubDiscusins"));
        url = locatorActions.getAttribute(by, "href");
        flag = locatorActions.verifyBrokenLink(url);
        Assert.assertTrue(flag);





    }

    public void userRefreshPage() {
        locatorActions.refreshPage();

    }

    public void usrOpenNewTab() {
        locatorActions.openNewTab();
        locatorActions.navigateUrl(ConfigDetails.getAutUrl());
    }
}
