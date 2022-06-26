package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utils.CustomAssertions.assertThatElementsTextContain;

public class GoogleSearchResultsPage extends BasePage{
    @FindBy(xpath = "//h3[@class and parent::a]")
    private List<WebElement> mainLinksInSearchResults;

    @Step("Get text of links in search results")
    public GoogleSearchResultsPage assertThatMainLinksContainSearchPhrase(String expectedText) {
        assertThatElementsTextContain(mainLinksInSearchResults, expectedText);
        return this;
    }
    @Step("Check if results page contains heading for videos section")
    public String getHeadingWihVideosName() {
        String videosSectionName = mainLinksInSearchResults.get(1).getText();
        log().info("Videos section name: " + videosSectionName);
        return videosSectionName;
    }

}
