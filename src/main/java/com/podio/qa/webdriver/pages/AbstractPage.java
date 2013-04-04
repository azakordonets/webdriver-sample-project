package com.podio.qa.webdriver.pages;

import com.google.common.base.Predicate;
import com.podio.qa.webdriver.data.TestPropertiesLoader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class for all Muse web pages
 */
public abstract class AbstractPage<T extends SlowLoadableComponent<T>> extends SlowLoadableComponent<T> {
    protected WebDriver driver;

    public static final long SHORT_WAIT_IN_MILISEC = 500;
    public static final int LONG_TIMEOUT_IN_SEC = 3;

    public String baseUrl = TestPropertiesLoader.getServer();

//    @FindBy(className = "fade")
//    protected WebElement uiLoadingPageFade;


    public AbstractPage(WebDriver driver) {
        this(driver, LONG_TIMEOUT_IN_SEC);
    }

    public AbstractPage(WebDriver driver, int loadTimeout) {
        super(new SystemClock(), loadTimeout);
        this.driver = driver;
    }

    protected abstract String getPath();

    public String getUrl() {
        return baseUrl + getPath();
    }

    /**
     * Should check that all ajax calls are finished
     */
    protected void waitForAjax() {
        new WebDriverWait(driver, LONG_TIMEOUT_IN_SEC, SHORT_WAIT_IN_MILISEC).until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(final WebDriver input) {
                return jQueryActiveCount() == 0;
            }
        });
    }

    private long jQueryActiveCount() throws WebDriverException {
        return (Long) ((JavascriptExecutor) driver).executeScript(
                "if (typeof jQuery !== \"undefined\") " +
                        "{ return window.jQuery.active }" +
                        "else { return 0 }");
    }

    public boolean isUrlCorrect() {
        return driver.getCurrentUrl().equals(getUrl());
    }

    public void sendKeys(WebElement element, CharSequence text) {
        element.clear();
        element.sendKeys(text);
    }

    @Override
    protected void isLoaded() throws Error {
//        try {
//            Assert.assertFalse(uiLoadingPageFade.isDisplayed(), "Progress indicator is still displayed on " + this.getClass().getName());
//        } catch (NoSuchElementException e) {
//            //seems like uiLoadingPageFade is not present on this page
//        } catch (NullPointerException e) {
//            Assert.fail("fade is null");
//        }
    }

}
