package com.podio.qa.webdriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.podio.qa.webdriver.data.TestPropertiesLoader;
import com.podio.qa.webdriver.impl.MyHtmlUnitDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Properties;

/**
 * @author vgrigoruk
 *         Date: 3/11/13
 */
public class WebDriverFactory {

    private static Properties testProperties = TestPropertiesLoader.getProperties();
    private static String browserProperty = testProperties.getProperty(TestPropertiesLoader.KEY_BROWSER, TestPropertiesLoader.BROWSER_FF);
    private static String chromeDriverPath = testProperties.getProperty(TestPropertiesLoader.KEY_CHROME_DRIVER_PATH);

    private WebDriverFactory() {
    }

    public static WebDriver createDriver() {
        return createDriver(browserProperty);
    }

    /**
     * Avoid using this method in your tests. Instead, use {@code WebDriverFactory.createDriver()}
     *
     * @param browserName - "firefox", "chrome", "htmlunit.
     * @return ready-for-use instance of webdriver
     */
    public static WebDriver createDriver(String browserName) {
        WebDriver driver;
        if (browserName.equals(TestPropertiesLoader.BROWSER_FF)) {
            driver = buildFirefoxDriver();
        } else if (browserName.equals(TestPropertiesLoader.BROWSER_CHROME)) {
            driver = buildChromeDriver();
        } else if (browserName.equals(TestPropertiesLoader.BROWSER_HTML_UNIT)) {
            driver = buildHtmlUnitDriver();
        } else driver = buildFirefoxDriver();

        return driver;

    }

    private static WebDriver buildFirefoxDriver() {
        return new FirefoxDriver();
    }

    private static WebDriver buildChromeDriver() {
        if (chromeDriverPath == null || !new File(chromeDriverPath).exists()) {
            throw new RuntimeException("Invalid path to chromedriver: " + chromeDriverPath + ". \nPlease define " + TestPropertiesLoader.KEY_CHROME_DRIVER_PATH + " system property");
        }
        WebDriver chromeDriver = new ChromeDriver();
        return chromeDriver;
    }

    private static WebDriver buildHtmlUnitDriver() {
        MyHtmlUnitDriver htmlUnitDriver = new MyHtmlUnitDriver(BrowserVersion.CHROME_16);
        htmlUnitDriver.setJavascriptEnabled(true);
        return htmlUnitDriver;
    }

}
