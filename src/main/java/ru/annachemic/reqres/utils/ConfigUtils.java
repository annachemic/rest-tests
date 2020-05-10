package ru.annachemic.reqres.utils;

import lombok.experimental.UtilityClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class ConfigUtils {

    public String getBaseUrl() throws IOException {
        InputStream input = new FileInputStream("src/test/resources/application.properties");
        Properties prop = new Properties();
        prop.load(input);
        return prop.getProperty("url");
    }
}
