package ru.itis.util;

import lombok.experimental.UtilityClass;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertyReader {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream("app.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}