package com.automation.utils;

import com.automation.exceptions.CustomException;
import com.automation.exceptions.CustomExceptionType;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileDirUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDirUtils.class);

    public static final String UTF_8 = "UTF-8";

    public void copyFile(File src, File dst) {
        try {
            FileUtils.copyFile(src, dst);
        } catch (IOException e) {
            LOGGER.error("failed to copy file");
            throw new CustomException(CustomExceptionType.PROCESSING_FAILED, "failed copy file");
        }
    }

    public byte[] readFileToByteArray(final String filename) {
        try {
            return FileUtils.readFileToByteArray(new File(filename));
        } catch (IOException e) {
            LOGGER.error("CustomException occurred while reading File [{}] to byte array", filename, e);
            throw new CustomException(CustomExceptionType.IO_ERROR, "CustomException occurred while reading File [{}] to byte array", filename, e);
        }
    }


}
