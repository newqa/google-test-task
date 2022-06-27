package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutGmailPage extends BasePage {

    @FindBy(css = "a[data-action='sign in']")
    private WebElement signInButton;

    public String getSignInButtonText() {
        return signInButton.getText();
    }

}
