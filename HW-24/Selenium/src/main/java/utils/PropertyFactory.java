package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFactory {
    protected static FileInputStream fileInputStream;
    protected static Properties properties;

    static {
        try {
            fileInputStream = new FileInputStream("src/main/resources/project.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String property(String key) {
        return properties.getProperty(key);
    }
}
