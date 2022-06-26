package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utils.CustomAssertions.assertThatElementsTextContain;
import static utils.Waits.waitUntilElementIsVisible;

public class GoogleSearchResultsPage extends BasePage {
    @FindBy(xpath = "//h3[@class and parent::a]")
    private List<WebElement> mainLinksInSearchResults;

    @FindBy(xpath = "//h3[@class='GmE3X' and ancestor::div[@class='ULSxyf']]")
    private WebElement videosSectionHeading;
    @FindBy(css = "video-voyager")
    private List<WebElement> youtubeVideosLinks;

    @FindBy(xpath = "//a[contains(@aria-label, 'Page')]")
    private List<WebElement> redirectionsToNextResultPages;

    @FindBy(css = "td[class='YyVfkd']")
    private WebElement currentPage;

    @FindBy(name = "SJajHc NVbCr")
    private List<WebElement> redirectionsToNextResultPagesFromGoooogleLogo;

    @FindBy(xpath = "//span[text()='Następna']")
    private WebElement nextResultsPage;

    @FindBy(xpath = "//span[text()='Poprzednia']")
    private WebElement previousResultsPage;

    @Step("Get text of links in search results")
    public GoogleSearchResultsPage assertThatMainLinksContainSearchPhrase(String expectedText) {
        assertThatElementsTextContain(mainLinksInSearchResults, expectedText);
        return this;
    }

    @Step("Check if results page contains heading for videos section")
    public String getHeadingWihVideosName() {
        String videosSectionName = videosSectionHeading.getText();
        log().info("Videos section name: " + videosSectionName);
        return videosSectionName;
    }

    @Step("Check the number of videos in the videos section")
    public Integer getNumberOfVideosInSearchResults() {
        int numberOfVideos = youtubeVideosLinks.size();
        log().info("Number of Youtube videos: " + numberOfVideos);
        return numberOfVideos;
    }

    @Step("Check the number of redirections to result pages")
    public Integer getNumberOfResultPages() {
        int numberOfResultPages = redirectionsToNextResultPages.size();
        log().info("Number of result pages: " + numberOfResultPages);
        return numberOfResultPages;
    }

    @Step("Get the current page of search results")
    public Integer getCurrentPageNumber() {
        String currentPageNr = currentPage.getText();
        log().info("Current page: " + currentPageNr);
        return Integer.parseInt(currentPageNr);
    }

    @Step("Change result page by clicking Next")
    public GoogleSearchResultsPage clickNext (){
        nextResultsPage.click();
        log().info("Clicking on " + nextResultsPage.getText());
        return this;
    }

    @Step("Change result page to previous by clicking Previous")
    public GoogleSearchResultsPage clickPrevious (){
        previousResultsPage.click();
        log().info("Clicking on previous page");
        return this;
    }

}
