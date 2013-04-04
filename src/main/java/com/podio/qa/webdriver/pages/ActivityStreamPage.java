package com.podio.qa.webdriver.pages;

import com.podio.qa.webdriver.data.UserCredentials;
import org.openqa.selenium.WebDriver;

/**
 * @author vgrigoruk
 *         Date: 4/4/13
 */
public class ActivityStreamPage extends AbstractAuthorizedPage<ActivityStreamPage> {
    public ActivityStreamPage(WebDriver driver) {
        super(driver);
    }

    public ActivityStreamPage(WebDriver driver, LoginPage loginPage) {
        super(driver, loginPage);
    }

    public ActivityStreamPage(WebDriver driver, LoginPage loginPage, UserCredentials creds) {
        super(driver, loginPage, creds);
    }

    @Override
    protected String getPath() {
        return "/stream";
    }
}
