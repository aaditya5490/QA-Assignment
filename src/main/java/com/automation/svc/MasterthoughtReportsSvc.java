package com.automation.svc;


import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MasterthoughtReportsSvc {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterthoughtReportsSvc.class);
    private static String targetDir = "target/cucumber-advanced-reports";


    private static String getTargetDir() {
        return targetDir;
    }

    public static List<String> getAllJsonFilesUnderTarget(String folderLocation) {
        List<String> jsonFiles = new ArrayList<>();
        File directory = new File(folderLocation);
        File[] files = directory.listFiles((file, name) -> name.endsWith(".json"));
        if (files != null && files.length > 0) {
            for (File f : files) {
                jsonFiles.add(folderLocation + "/" + f.getName());
            }
        }
        return jsonFiles;
    }

    public static void generateReport() {
        File reportOutputDirectory = new File(getTargetDir());
        List<String> jsonFiles = getAllJsonFilesUnderTarget(getTargetDir());
        String projectName = "AFN Automation";
        String buildNumber = "1.0";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);
        configuration.addClassifications("Platform", System.getProperty("os.name").toUpperCase());
        configuration.addClassifications("Browser", System.getProperty("Browser"));
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }


}
