package com.automation.config;

import com.automation.utils.LoggerUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class BrowserLib {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserLib.class);
    public static WebDriver selectedDriver = null;



    public static WebDriver launchDriver() {
        LoggerUtil.configureLogging();
        String desiredBrowser =  System.getProperty("browser");;
        switch (desiredBrowser) {
            case "chrome":
                LOGGER.info("************Launching Chrome Browser**********");
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                selectedDriver = new ChromeDriver(chromeOptions);
                break;
            case "chrome-headless":
                LOGGER.info("************Launching Chrome Headless Browser**********");
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--remote-debugging-port=9222");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                selectedDriver = new ChromeDriver(options);
                break;
            case "firefox":
                LOGGER.info("************Launching Firefox Browser**********");
                WebDriverManager.firefoxdriver().setup();
                selectedDriver = new FirefoxDriver();
                break;
            case "ie":
                LOGGER.info("************Launching IE Browser**********");
                WebDriverManager.iedriver().setup();
                selectedDriver = new InternetExplorerDriver();
                break;
            case "safari":
                LOGGER.info("************Launching Safari Browser**********");
                selectedDriver = new SafariDriver();
                break;
            case "opera":
                LOGGER.info("************Launching opera Browser**********");
                WebDriverManager.operadriver().setup();
                selectedDriver = new OperaDriver();
                break;
            default:
                throw new NotImplementedException("Desired Browser is not Found");
        }
        LOGGER.info("-------Execution Started-------");
        selectedDriver.manage().deleteAllCookies();
        selectedDriver.manage().window().maximize();
        return selectedDriver;
    }

  /*  private static void acceptCookies() {
        selectedDriver.findElement(By.id("o-cookiePolicyAccept")).click();
    }
*/
    public static void launchUrl(String url) {
        LOGGER.info("** Launching to " + url + "***");
        selectedDriver.get(url);
    }


    public WebDriver getDriver() {
        return selectedDriver;
    }

    public static void quitDriver() {
         selectedDriver.quit();
    }


    /*public void configurePreprodAuthenticationExtension() {
        if (ConfigDetails.getEnv().equalsIgnoreCase("preprod")) {
            selectedDriver.get("chrome-extension://enhldmjbphoeibbpdhmjkchohnidgnah/popin.html");
            selectedDriver.findElement(By.id("url")).sendKeys("https://preprod.asianfoodnetwork.com/");
            selectedDriver.findElement(By.id("username")).sendKeys(ConfigDetails.getAuthUn());
            selectedDriver.findElement(By.id("password")).sendKeys(ConfigDetails.getAuthUn());
            selectedDriver.findElement(By.xpath("//button[text()='Add']")).click();
        }
*/
    }



