package com.usermanage.data.selenium;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            // Loads config.properties directly from src/test/resources (the classpath)
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Could not find config.properties in src/test/resources");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
 
        return properties.getProperty(key);
        
    }
}