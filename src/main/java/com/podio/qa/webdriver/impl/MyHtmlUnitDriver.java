package com.podio.qa.webdriver.impl;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * @author vgrigoruk
 *         Date: 3/19/13
 */
public class MyHtmlUnitDriver extends HtmlUnitDriver {
    public MyHtmlUnitDriver(BrowserVersion version) {
        super(version);
    }

    public MyHtmlUnitDriver() {
        super();
    }

    public MyHtmlUnitDriver(boolean enableJavascript) {
        super(enableJavascript);
    }

    public MyHtmlUnitDriver(Capabilities capabilities) {
        super(capabilities);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected WebClient modifyWebClient(WebClient client) {
        //currently does nothing, but may be changed in future versions
        WebClient modifiedClient = super.modifyWebClient(client);

        modifiedClient.getOptions().setThrowExceptionOnScriptError(false);
        return modifiedClient;
    }
}
