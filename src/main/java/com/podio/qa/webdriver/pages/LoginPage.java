package com.podio.qa.webdriver.pages;

import com.podio.qa.webdriver.data.UserCredentials;
import junit.framework.Assert;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * @author vgrigoruk
 *         Date: 3/18/13
 */
public class LoginPage extends AbstractPage<LoginPage> {

    @FindBy(css = "li.form-error-message.notice")
    private WebElement noticeMessage;

    @FindBy(css = "input[autofocus='autofocus'][class='required email']")
    private WebElement inputEmail;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(css = "button.flatbutton.green")
    private WebElement loginButton;

    private MainPage mainPage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage(WebDriver driver, MainPage mainPage) {
        super(driver);
        this.mainPage = mainPage;
    }

    @Override
    protected String getPath() {
        return "/login";
    }

    public ActivityStreamPage doValidSignIn(String username, String password) {
        new WebDriverWait(driver, LONG_TIMEOUT_IN_SEC).until(
                ExpectedConditions.visibilityOf(inputEmail)
        );
        sendKeys(inputEmail, username);
        sendKeys(inputPassword, password);
        loginButton.click();
        waitForAjax();
        return HtmlElementLoader.createPageObject(ActivityStreamPage.class, driver);
    }

    public ActivityStreamPage doValidSignIn(UserCredentials credentials) {
        return doValidSignIn(credentials.getUsername(), credentials.getPassword());
    }

    public String getNoticeMessage() {
        try {
            return noticeMessage.getText();
        } catch (NotFoundException e) {
            return null;
        }
    }

    @Override
    protected void load() {
        mainPage.get()
                .clickSignIn();
        waitForAjax();
        HtmlElementLoader.populatePageObject(this, driver);
    }

    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();
        try {
            Assert.assertTrue(inputEmail.isDisplayed());
            Assert.assertTrue(inputPassword.isDisplayed());
            Assert.assertTrue(loginButton.isDisplayed());
        } catch (NullPointerException npe) {
            throw new Error("Login elements are not initialized yet");
        }
    }
}
