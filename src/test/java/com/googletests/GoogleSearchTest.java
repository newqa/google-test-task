package com.googletests;

import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pageobjects.GoogleHomePage;
import pageobjects.GoogleSearchResultsPage;
import utils.listeners.RetryAnalyzer;

public class GoogleSearchTest extends TestBase {
    private GoogleHomePage googleHomePage;
    private GoogleSearchResultsPage googleSearchResultsPage;
    private String searchedPhrase = "Selenium";
    private String expectedVideosSectionName = "Wideo";
    private int expectedYoutubeVideosNumber = 3;
    private int expectedNumberOfRedirectionsToResultPages = 9;
    private int expectedCurrentFirstPageNumber = 1;
    private int expectedCurrentSecondPageNumber = 2;

    @Description("Search in google")
    @Test(groups = "chromeOnlyTests")
    public void shouldBeAbleToGetLinksWhichContainSearchedPhrase() {
        googleHomePage = new GoogleHomePage();
        googleHomePage
                .acceptAllCookies()
                .searchWithText(searchedPhrase)
                .assertThatMainLinksContainSearchPhrase(searchedPhrase);
    }

    @Description("Verify that search results page contains videos section with three sub-sections")
    @Test(groups = "chromeOnlyTests",
            retryAnalyzer = RetryAnalyzer.class)
    public void shouldBeAbleToGetVideoSectionOnResultsPage() {
        googleSearchResultsPage = new GoogleSearchResultsPage();
        googleHomePage = new GoogleHomePage();
        String videosHeading = googleHomePage
                .acceptAllCookies()
                .searchWithText(searchedPhrase)
                .getHeadingWihVideosName();

        int numberOfYoutubeVideosLinks = googleSearchResultsPage.getNumberOfVideosInSearchResults();

        SoftAssertions softly = new SoftAssertions();
        softly
                .assertThat(videosHeading)
                .describedAs("Videos section name is incorrect or not found " + expectedVideosSectionName)
                .isEqualTo(expectedVideosSectionName);

        softly.assertThat(numberOfYoutubeVideosLinks)
                .describedAs("Youtube videos number is not equal to " + expectedYoutubeVideosNumber)
                .isEqualTo(expectedYoutubeVideosNumber);

        softly.assertAll();
    }

    @Description("Verify that user can navigate to other search results pages")
    @Test()
    public void shouldBeAbleToNavigateToOtherSearchResultsPages() {
        googleHomePage = new GoogleHomePage();
        googleSearchResultsPage = new GoogleSearchResultsPage();
        googleHomePage
                .acceptAllCookies()
                .searchWithText(searchedPhrase);
        int numberOfResultPages = googleSearchResultsPage
                .getNumberOfResultPages();
        int currentSearchResultsPage = googleSearchResultsPage
                .getCurrentPageNumber();
        int currentPageAfterClickingNext = googleSearchResultsPage
                .clickNext()
                .getCurrentPageNumber();
        int currentPageAfterClickingPrevious = googleSearchResultsPage
                .clickPrevious()
                .getCurrentPageNumber();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(numberOfResultPages)
                .describedAs("Number of redirections to result pages is not equal to " +
                        expectedNumberOfRedirectionsToResultPages)
                .isEqualTo(expectedNumberOfRedirectionsToResultPages);

        softly.assertThat(currentSearchResultsPage)
                .describedAs("Current page is not as expected " +
                        expectedCurrentFirstPageNumber)
                .isEqualTo(expectedCurrentFirstPageNumber);

        softly.assertThat(currentPageAfterClickingNext)
                .describedAs("Current page after clicking next is not as expected " +
                        expectedCurrentSecondPageNumber)
                .isEqualTo(expectedCurrentSecondPageNumber);

        softly.assertThat(currentPageAfterClickingPrevious)
                .describedAs("Current page after clicking next is not as expected " +
                        expectedCurrentFirstPageNumber)
                .isEqualTo(expectedCurrentFirstPageNumber);

        softly.assertAll();

    }
}
