package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomAssertions {

    private static Logger logger = LogManager.getLogger(CustomAssertions.class.getName());

    private static final String ELEMENT_TEXT = "The element text %s";

    public static void assertThatElementTextContains(WebElement element, String expectedText) {
        String elementText = element.getText();
        logger.info("Element text " + elementText);
        assertThat(elementText)
                .describedAs(String.format(ELEMENT_TEXT, "contains expected text."))
                .containsIgnoringCase(expectedText);
    }

    public static void assertThatElementsTextContain(List<WebElement> webElements, String expectedText) {
        webElements
                .forEach(webElement -> assertThatElementTextContains(webElement, expectedText));
    }

}
