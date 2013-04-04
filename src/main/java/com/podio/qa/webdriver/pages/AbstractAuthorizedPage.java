package com.podio.qa.webdriver.pages;

import com.podio.qa.webdriver.data.UserCredentials;
import com.podio.qa.webdriver.pages.blocks.ProfileBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * @author vgrigoruk
 *         Date: 3/19/13
 */
public abstract class AbstractAuthorizedPage<T extends SlowLoadableComponent<T>> extends AbstractPage<T> {

    @FindBy(css = "li.profile")
    ProfileBlock profileBlockControls;

    protected LoginPage loginPage;
    private UserCredentials credentials;

    public AbstractAuthorizedPage(WebDriver driver) {
        super(driver);
    }

    public AbstractAuthorizedPage(WebDriver driver, LoginPage loginPage) {
        this(driver);
        this.loginPage = loginPage;
    }

    public AbstractAuthorizedPage(WebDriver driver, LoginPage loginPage, UserCredentials creds) {
        this(driver, loginPage);
        this.credentials = creds;
    }

    public LoginPage doLogout() {
        waitForAjax();
        profileBlockControls.myAccountLink.click();
        HtmlElementLoader.populateHtmlElement(profileBlockControls, driver);
        profileBlockControls.logoutLink.click();
        waitForAjax();

        return HtmlElementLoader.createPageObject(LoginPage.class, driver);
    }

    @Override
    protected void load() {
        loginPage.get();
        loginPage.doValidSignIn(credentials.getUsername(), credentials.getPassword());
        HtmlElementLoader.populatePageObject(this, driver);
    }

//    @Override
//    protected void isLoaded() throws Error {
//        Assert.assertTrue(logoutLink != null && logoutLink.isDisplayed());
//    }
}
