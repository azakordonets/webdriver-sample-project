package com.podio.qa.webdriver.pages.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author vgrigoruk
 *         Date: 4/4/13
 */
@Name("User profile block")
@Block(@FindBy(css = "li.profile"))
public class ProfileBlock extends HtmlElement {

    @FindBy(css = "a.user-name")
    public WebElement username;

    @Name("Edit profile link ('My account')")
    @FindBy(css = "a.edit-profile")
    public WebElement myAccountLink;

    //TODO: add all elements from User profile block

    @FindBy(partialLinkText = "Logout")
    public WebElement logoutLink;
}
