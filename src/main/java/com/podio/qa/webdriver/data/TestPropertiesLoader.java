package com.podio.qa.webdriver.data;

import com.podio.qa.webdriver.WebDriverFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author vgrigoruk
 *         Date: 3/27/13
 */
public class TestPropertiesLoader {
    private static Properties testProperties;

    public static final String KEY_BROWSER = "browser";
    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_FF = "firefox";
    public static final String BROWSER_HTML_UNIT = "htmlunit";
    public static final String KEY_CHROME_DRIVER_PATH = "webdriver.chrome.driver";
    public static final String KEY_SERVER = "server";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";


    public static final String TEST_PROPERTIES = "test.properties";


    static {
        testProperties = loadProperties();
        testProperties.putAll(System.getProperties());
    }

    private static Properties loadProperties() {
        InputStream is = WebDriverFactory.class.getClassLoader().getResourceAsStream(TEST_PROPERTIES);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            System.out.println("[INFO] " + TEST_PROPERTIES + " wasn't not found in classpath.");
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
        }
        return properties;
    }

    public static Properties getProperties() {
        return testProperties;
    }

    public static String getBrowser() {
        return getPropertyAndFailIfMissing(KEY_BROWSER);
    }

    public static String getChromeDriverPath() {
        return getPropertyAndFailIfMissing(KEY_CHROME_DRIVER_PATH);
    }

    public static String getServer() {
        return getPropertyAndFailIfMissing(KEY_SERVER);
    }

    public static String getLogin() {
        return getPropertyAndFailIfMissing(KEY_LOGIN);
    }

    public static String getPassword() {
        return getPropertyAndFailIfMissing(KEY_PASSWORD);
    }

    private static String getPropertyAndFailIfMissing(String key) {
        String value = testProperties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(key + " system property is not defined");
        }
        return value;
    }
}
