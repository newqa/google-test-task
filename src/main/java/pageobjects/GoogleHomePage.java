package pageobjects;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waits;

@Slf4j
public class GoogleHomePage extends BasePage {

    @FindBy(name = "q")
    private WebElement googleSearchField;

    @FindBy(id = "L2AGLb")
    private WebElement acceptAllCookiesButton;

    @FindBy(id = "SIvCob")
    private WebElement languagesToUseInfo;

    @FindBy(xpath = "//a[@href and parent::div[@id='SIvCob']]")
    private WebElement languageLink;

    @FindBy(className = "gb_d")
    private WebElement gmail;

    @FindBy(className = "gb_A")
    private WebElement googleApplicationsButton;

    @FindBy(css = "iframe")
    private WebElement googleApplications;

    @Step("Accept all cookies")
    public GoogleHomePage acceptAllCookies() {
        acceptAllCookiesButton.click();
        return this;
    }

    @Step("Search with text {text}")
    public GoogleSearchResultsPage searchWithText(String text) {
        log().info("Clearing search field");
        googleSearchField.clear();
        log().info("Searching for text " + text);
        googleSearchField.sendKeys(text);
        googleSearchField.sendKeys(Keys.ENTER);
        return new GoogleSearchResultsPage();
    }

    @Step("Get suggested language")
    public String getSuggestedLanguage() {
        String suggestedLanguage = languagesToUseInfo.getText();
        log().info("Suggested language " + suggestedLanguage);
        return suggestedLanguage;
    }

    @Step("Change language")
    public GoogleHomePage changeLanguage() {
        languageLink.click();
        log().info("Changed language to suggested one");
        return this;
    }

    @Step("Click on Gmail hyperlink")
    public String clickOnGmailHyperLink() {
        String gmailText = gmail.getText();
        gmail.click();
        log().info("Clicked on Gmail hyperlink");
        return gmailText;
    }

    @Step("Click on Google applications")
    public Boolean clickOnGoogleApplicationsButton() {
        googleApplicationsButton.click();
        Waits.waitUntilElementIsVisible(googleApplications);
        log().info("Iframe is displayed " + googleApplications.isDisplayed());
        return googleApplications.isDisplayed();
    }

}
