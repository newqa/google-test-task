package com.googletests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pageobjects.GoogleHomePage;
import pageobjects.GoogleSearchResultsPage;

public class GoogleSearchTest extends TestBase {
    private GoogleHomePage googleSearchPage;
    private GoogleSearchResultsPage googleSearchResultsPage;
    private String searchedPhrase = "Selenium";

    @Description("Search in google")
    @Test(groups = "chromeOnlyTests")
    public void shouldBeAbleToGetLinksWhichContainSearchedPhrase() {
        googleSearchPage = new GoogleHomePage();
        googleSearchPage
                .acceptAllCookies()
                .searchWithText(searchedPhrase)
                .assertThatMainLinksContainSearchPhrase(searchedPhrase);
    }

    @Description("Search in google")
    @Test(groups = "chromeOnlyTests")
    public void shouldBeAbleToGetVideoSectionWhichContainContentRelatedWithSearchedPhrase() {
        googleSearchPage = new GoogleHomePage();
        googleSearchPage
                .acceptAllCookies()
                .searchWithText(searchedPhrase)
                .getHeadingWihVideosName();
    }

}
