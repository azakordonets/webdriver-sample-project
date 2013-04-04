package com.podio.qa.webdriver;

import com.podio.qa.webdriver.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

/**
 * @author vgrigoruk
 *         Date: 3/19/13
 */
public abstract class WebdriverTestBase {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(AbstractPage.LONG_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    protected void assertCurrentUrlStartsWith(String prefix) {
        Assert.assertTrue(driver.getCurrentUrl().startsWith(prefix), "Incorrect page URL, expected to start with:  " + prefix + ", actual: " + driver.getCurrentUrl());
    }
}
