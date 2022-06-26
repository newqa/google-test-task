package com.googletests;

import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pageobjects.AboutGmailPage;
import pageobjects.GoogleHomePage;

public class GoogleHomePageTests extends TestBase{
    private GoogleHomePage googleHomePage;
    private AboutGmailPage aboutGmailPage;

    @Description("Verify Gmail hyperlink on main Google page")
    @Test(groups = "chromeOnlyTests")
    public void shouldBeAbleToClickOnGmailLinkAndBeRedirectedToGmailPage() {
        googleHomePage = new GoogleHomePage();
        aboutGmailPage = new AboutGmailPage();

        String gmailText = googleHomePage
                .acceptAllCookies()
                .clickOnGmailHyperLink();

        String signInButtonText = aboutGmailPage
                .getSignInButtonText();

        SoftAssertions softly = new SoftAssertions();
        softly
                .assertThat(gmailText)
                .describedAs("Gmail hyperlink display name is incorrect.")
                .isEqualTo("Gmail");
        softly
                .assertThat(signInButtonText)
                .describedAs("Sign in button text is incorrect")
                .isEqualTo("Zaloguj się");
        softly.assertAll();

    }

    @Description("Verify that applications frame appears when applications button is clicked")
    @Test
    public void shouldBeAbleToExpandGoogleApplications() {
        googleHomePage = new GoogleHomePage();
        boolean isApplicationsFrameDisplayed = googleHomePage
                .acceptAllCookies()
                .clickOnGoogleApplicationsButton();

        SoftAssertions softly = new SoftAssertions();
        softly
                .assertThat(isApplicationsFrameDisplayed)
                .describedAs("Google applications are not displayed.")
                .isTrue();
        softly.assertAll();
    }

    @Description("Verify current language and possibility to change language used in Google")
    @Test
    public void shouldBeAbleToChangeLanguageToSuggestedOne() {
        googleHomePage = new GoogleHomePage();
        String suggestedLanguage = googleHomePage
                .acceptAllCookies()
                .getSuggestedLanguage();

        googleHomePage.changeLanguage();

        String changedLanguage = googleHomePage
                .getSuggestedLanguage();

        SoftAssertions softly = new SoftAssertions();
        softly
                .assertThat(suggestedLanguage)
                .describedAs("Suggested languages message is not as expected.")
                .isEqualTo("Korzystaj z Google w tych językach: English");
        softly
                .assertThat(changedLanguage)
                .describedAs("Language is not changed")
                .isEqualTo("Google offered in: polski");
        softly.assertAll();

    }

}
