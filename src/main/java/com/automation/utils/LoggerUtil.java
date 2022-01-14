package com.automation.utils;

import com.google.common.base.Strings;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoggerUtil {

    public static void configureLogging() {
        String logConfigLocation = getLogConfigLocation();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("currenttime", dateFormat.format(new Date()));
        try {
            DOMConfigurator.configure(new URL(logConfigLocation));
        } catch (MalformedURLException e) {
            System.out.println("FATAL: failed to configure Logging." + e.getMessage());
            System.exit(3);
        }
    }

    private static String getLogConfigLocation() {
        String logConfigLocation = System.getProperty("log4j.config");
        if (Strings.isNullOrEmpty(logConfigLocation)) {
            logConfigLocation = "file:src/main/resources/log4j.xml";
        }
        return logConfigLocation;
    }


}