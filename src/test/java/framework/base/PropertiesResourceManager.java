package framework.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesResourceManager {

    private static final String EXTENSION = ".properties";
    private static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource %s not found";
    private static final String CANT_READ_PROPERTIES_MESSAGE = "Can't read properties from %s";
    private Properties properties = new Properties();

    public PropertiesResourceManager(String resourceName) {
        String resourceNameWithExtension = resourceName + EXTENSION;
        InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream(resourceNameWithExtension);
        if (propertiesStream != null) {
            try {
                properties.load(propertiesStream);
                propertiesStream.close();
            } catch (IOException e) {
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getSystemProperty(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }
}
