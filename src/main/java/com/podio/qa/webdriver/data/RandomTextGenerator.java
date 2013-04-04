package com.podio.qa.webdriver.data;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * @author vgrigoruk
 *         Date: 3/26/13
 */
public class RandomTextGenerator extends LoremIpsum {

    public String getCharacters(int number) {
        String baseStr = super.getWords();
        if (number > baseStr.length()) {
            StringBuilder sb = new StringBuilder(number);
            while (sb.length() < number) {
                sb.append(baseStr);
            }
            baseStr = sb.toString();
        }
        return baseStr.substring(0, number);
    }
}
