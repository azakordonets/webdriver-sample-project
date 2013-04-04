package com.podio.qa.webdriver.data;

/**
 * @author vgrigoruk
 *         Date: 3/20/13
 */
public class TestDataProvider {
    private static final UserCredentials defaultCredentials =
            new UserCredentials(TestPropertiesLoader.getLogin(), TestPropertiesLoader.getPassword());

    public static UserCredentials getUserCredentials() {
        return defaultCredentials;
    }
}
