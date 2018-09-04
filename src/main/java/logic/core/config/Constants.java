package logic.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Constants {
    public final static String PATH_TO_LANGUAGE_DIRECTORY = getValue("PATH_TO_LANGUAGE_DIRECTORY");
    public final static String EXCEL_SHEET_NAME = getValue("EXCEL_SHEET_NAME");
    public final static String EXCEL_2007_EXTENSION = ".xlsx";

    public class Messages{
        public final static String REPORT_SUCCESS_CREATING = "Report was created successfully!";
        public final static String REPORT_SUCCESS_UPDATING = "Report was updated successfully!";
    }

    private static String getValue(String name) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("config.properties")));
            return properties.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }

    }
}
