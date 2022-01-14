package com.automation.utils;

import com.automation.config.BrowserLib;
import com.automation.exceptions.CustomException;
import com.automation.exceptions.CustomExceptionType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LocatorActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocatorActions.class);

    private static final String ELEMENT_IS_NOT_VISIBLE = "Element [{}] is not visible";
    private static final String EXCEPTION_WHILE_QUITTING_DRIVER = "Exception while quitting driver";
    public static final String SCREENSHOT_PATH = "target/cucumber-advanced-reports/screenshots";

    public static final Integer DEFAULT_IMPLICIT_TIMEOUT = 10;
    private Hashtable<String, Integer> namePrefices = new Hashtable<>();
    private WebDriver driver;
    private boolean sessionEstablished = false;

    @Autowired
    private BrowserLib browserLib;

    @Autowired
    private FileDirUtils fileDirUtils;

    @Autowired
    private ScenarioUtils scenarioUtils;

    public WebDriver getDriver() {
        if (!sessionEstablished) {
            driver = browserLib.getDriver();
            sessionEstablished = true;
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        }
        return driver;
    }

    public By constructByObjectFromExpression(final String expression) {
        List<String> locator = getLocatorStrategyFromString(expression, ":");
        return this.constructByObject(locator.get(0), locator.get(1));
    }


    public List<String> getLocatorStrategyFromString(final String expression, final String separator) {
        ArrayList<String> locatorList = new ArrayList<String>();
        int separatorIndex = expression.indexOf(separator);
        String locatorType = expression.substring(0, separatorIndex);
        String locator = expression.substring(separatorIndex + 1);
        locatorList.add(locatorType);
        locatorList.add(locator);
        return locatorList;

    }

    public By constructByObject(final String locator, final String identifier) {
        String locatorTyp = locator.toUpperCase();
        if (locatorTyp.equals("XPATH")) {
            return By.xpath(identifier);
        } else if (locatorTyp.equals("ID")) {
            return By.id(identifier);
        } else if (locatorTyp.equals("CLASSNAME")) {
            return By.className(identifier);
        } else if (locatorTyp.equals("CSS")) {
            return By.cssSelector(identifier);
        } else {
            LOGGER.error("Undefined Locator to construct By object");
            throw new CustomException(CustomExceptionType.UNDEFINED, "Undefined Locator to construct By object");
        }
    }


    public void setImplicitWaitTimeOutSeconds(final Integer seconds) {
        getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public WebDriverWait getWebDriverWait(final Integer timeoutInSeconds) {
        return new WebDriverWait(getDriver(), timeoutInSeconds);
    }

    public void navigateUrl(final String url) {
        LOGGER.info("** Navigating to " + url + "***");
        getDriver().navigate().to(url);
        waitTillPageIsOpened(30);
    }

    public void quitDriver() {
        try {
            if (driver != null) {
                driver.quit();
                LOGGER.info("Driver Stopped!!!");
            }
        } catch (WebDriverException e) {
            LOGGER.error(EXCEPTION_WHILE_QUITTING_DRIVER);
            throw new CustomException(CustomExceptionType.IO_ERROR, EXCEPTION_WHILE_QUITTING_DRIVER);

        } finally {
            sessionEstablished = false;
        }
    }

    public void moveMouseToElement(final WebElement element) {
        Actions action = new Actions(getDriver());
        action.moveToElement(element).build();
        LOGGER.info("Moved to Element");
    }

    public WebElement findElement(final By by) {
        try {
            getWebDriverWait(30)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            highlightElement(getDriver().findElement(by));
            return (WebElement) getDriver().findElement(by);
        } catch (Exception e) {
            LOGGER.error("Exception while finding element with By [{}]", by.toString(), e);
            throw new CustomException(CustomExceptionType.VALIDATION_FAILED, "Exception while finding element with By [{}]", by.toString());
        }
    }

    public boolean verifyElement(final By by) {
        boolean flag = false;
        try {
            getWebDriverWait(30)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(by));
            getDriver().findElement(by);
            highlightElement(getDriver().findElement(by));
            flag = true;
        } catch (Exception e) {
            LOGGER.error("Exception while finding element with By [{}]", by.toString(), e);
        } finally {
            return flag;
        }
    }

    public boolean click(WebElement element) {
        highlightElement(element);
        return this.click(element, 0);
    }

    public String getLabelText(final By by) {
        WebElement element = this.findElement(by);
        return element.getText();
    }

    public boolean click(final By by) {
        WebElement element = this.findElement(by);
        getWebDriverWait(30)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(TimeoutException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        return this.click(element, 30);
    }

    public boolean clickIfVisible(final By by) {
        boolean flag = false;
        try {
            WebElement element = this.findElement(by);
            getWebDriverWait(20)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(TimeoutException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));
            if (element.isEnabled() && element.isDisplayed()) {
                highlightElement(element);
                element.click();
                flag = true;
            }
        } catch (Exception e) {
            LOGGER.warn("Element not available with By [{}]", by.toString(), e);
        } finally {
            return flag;
        }
    }

    public void clickIfVisible(final WebElement element) {
        getWebDriverWait(30)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        if (element.isEnabled() && element.isDisplayed()) {
            highlightElement(element);
            element.click();
        }
    }

    public boolean click(WebElement element, final Integer timeOutInSeconds) {
        boolean flag = false;
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(TimeoutException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));
            highlightElement(element);
            element.click();
            flag = true;
        } catch (Exception e) {
            LOGGER.error("Element [{}] is not clickable", element.toString(), e);
            throw new CustomException(CustomExceptionType.PROCESSING_FAILED, "Element [{}] is not clickable", element.toString());
        } finally {
            return flag;
        }
    }

    public boolean sendKeys(WebElement element, String textToEnter) {
        boolean flag = false;
        try {
            getWebDriverWait(30)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));
            highlightElement(element);
            element.clear();
            element.sendKeys(textToEnter);
            flag = true;

        } catch (Exception e) {
            LOGGER.error("Element [{}] is not clickable", element.toString(), e);
            throw new CustomException(CustomExceptionType.PROCESSING_FAILED, "Element [{}] is not clickable", element.toString());
        } finally {
            return flag;
        }
    }

    public boolean sendKeys(By by, String textToEnter) {
        WebElement element = findElement(by);
        return sendKeys(element, textToEnter);
    }

    public void PressEnterKey(By by) {
        WebElement element = findElement(by);
        element.sendKeys(Keys.ENTER);
    }

    public boolean verifyElementIsVisible(WebElement element) {
        boolean flag = true;
        try {
            highlightElement(element);
            if (element != null && !element.isDisplayed()) {
                flag = false;
                LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString());
            }
        } catch (NoSuchElementException e) {
            flag = false;
            LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString(), e);
        }
        return flag;
    }

    public boolean verifyElementIsVisible(By by) {
        WebElement element = this.findElement(by);
        return verifyElementIsVisible(element);
    }


    public boolean verifyElementIsInVisible(By by, Integer timeOutInSeconds) {
        try {
            getWebDriverWait(timeOutInSeconds)
                    .until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean waitTillElementIsPresent(final By by, final Integer timeOutInSeconds) {
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean waitTillElementIsClickable(final By by, Integer timeOutInSeconds) {
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean waitTillElementIsPresent(WebElement element, Integer timeOutInSeconds) {
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public Integer generateScreenshotNumber(final String prefix) {
        Integer snapshotNumber = namePrefices.getOrDefault(prefix, 0);
        namePrefices.put(prefix, snapshotNumber + 1);
        return namePrefices.get(prefix);
    }

    public String takeScreenshot(final String prefix) {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        LOGGER.info("Capturing screenshot for {}", scenarioUtils.getScenarioName());
        String screenShotName = scenarioUtils.getScenarioName().replaceAll(" ", "_");
        String screenshotPath = SCREENSHOT_PATH + File.separator + screenShotName + "_" + this.generateScreenshotNumber(prefix) + ".png";
        File dst = new File(System.getProperty("user.dir") + File.separator + screenshotPath);
        fileDirUtils.copyFile(src, dst);
        LOGGER.info("Captured screenshot name {} ", screenShotName);
        byte[] bytes = fileDirUtils.readFileToByteArray(dst.getAbsolutePath());
        scenarioUtils.embed(bytes, "image/png", screenShotName);
        return screenShotName;
    }

    public String takeElementScreenshot(final WebElement element,final String prefix) {
        File src = element.getScreenshotAs(OutputType.FILE);
        LOGGER.info("Capturing screenshot for {}", scenarioUtils.getScenarioName());
        String screenShotName = scenarioUtils.getScenarioName().replaceAll(" ", "_");
        String screenshotPath = SCREENSHOT_PATH + File.separator + screenShotName + "_" + this.generateScreenshotNumber(prefix) + ".png";
        File dst = new File(System.getProperty("user.dir") + File.separator + screenshotPath);
        fileDirUtils.copyFile(src, dst);
        LOGGER.info("Captured screenshot name {} ", screenShotName);
        byte[] bytes = fileDirUtils.readFileToByteArray(dst.getAbsolutePath());
        scenarioUtils.embed(bytes, "image/png", screenShotName);
        return screenShotName;
    }

    public void highlightElement(WebElement elm) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].style.border='4px groove red'", elm);
//            threadUtil.sleepSeconds(1);
            js.executeScript("arguments[0].style.border=''", elm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public synchronized void takeScreenshot() {
        this.takeScreenshot("screenshot");
    }


    public boolean waitTillPageIsOpened(final Integer maxTimeOutInSeconds) {
        boolean isPageOpened = false;
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        isPageOpened = this.getWebDriverWait(maxTimeOutInSeconds).until(pageLoadCondition);

        return isPageOpened;
    }


    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public boolean scrollToElement(WebElement element) {
        boolean flag;
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].scrollIntoView();", element);
            flag = true;

        } catch (NoSuchElementException e) {
            flag = false;
            LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString(), e);
            throw new CustomException(CustomExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
        }
        return flag;
    }

    public boolean clickOnElementByJS(WebElement element) {
        boolean flag;
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
            LOGGER.info("Selecting option {} from list using JS", element.toString());
            flag = true;
        } catch (NoSuchElementException e) {
            LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString(), e);
            throw new CustomException(CustomExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
        }
        return flag;
    }


    public boolean navigateBack() {
        LOGGER.info("** Navigating back ***");
        getDriver().navigate().back();
        return waitTillPageIsOpened(30);
    }

    public boolean refreshPage() {
        LOGGER.info("** Refreshing page ***");
        getDriver().navigate().refresh();
        return waitTillPageIsOpened(40);
    }

}


