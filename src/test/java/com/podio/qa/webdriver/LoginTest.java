package com.podio.qa.webdriver;

import com.podio.qa.webdriver.data.TestDataProvider;
import com.podio.qa.webdriver.data.UserCredentials;
import com.podio.qa.webdriver.pages.ActivityStreamPage;
import com.podio.qa.webdriver.pages.LoginPage;
import com.podio.qa.webdriver.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author vgrigoruk
 *         Date: 3/18/13
 */
public class LoginTest extends WebdriverTestBase {
    private MainPage mainPage;
    private LoginPage loginPage;
    private ActivityStreamPage activityStreamPage;
    private UserCredentials credentials = TestDataProvider.getUserCredentials();

    @Test
    public void testSignInSignOut() {
        mainPage = new MainPage(driver).get();
        loginPage = mainPage.clickSignIn();
        activityStreamPage = loginPage.doValidSignIn(credentials);
        assertCurrentUrlStartsWith(activityStreamPage.getUrl());
        loginPage = activityStreamPage.doLogout();
        assertCurrentUrlStartsWith(loginPage.getUrl());
        Assert.assertEquals(loginPage.getNoticeMessage(), "You have been logged out.");
    }
}
