package com.podio.qa.webdriver.pages;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class MainPage extends AbstractPage<MainPage> {

    @FindBy(css = "li.login>a")
    WebElement signInLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPath() {
        return "/";
    }

    @Override
    protected void load() {
        driver.get(getUrl());
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(signInLink != null);
        Assert.assertTrue(signInLink.isDisplayed());
    }

    public LoginPage clickSignIn() {
        signInLink.click();
        waitForAjax();
        return HtmlElementLoader.createPageObject(LoginPage.class, driver);
    }
}
