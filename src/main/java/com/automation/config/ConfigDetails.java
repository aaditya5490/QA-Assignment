package com.automation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDetails {
    public static String autURL;
    public static String pageTitle;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDetails.class);

    public static String getAutUrl() {
        autURL = ReadPropertiesFileLib.getProperty("URL");
        System.out.println("autURL"+ autURL);
        return autURL;
    }

    public static String getPageTitle() {
        pageTitle = System.getProperty("pageTitle", ReadPropertiesFileLib.getProperty("page.title"));
        return pageTitle;
    }

}
