package pageobjects;

import configuration.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    private Logger logger = LogManager.getLogger(this.getClass().getName());

    protected Logger log() {
        return logger;
    }

}

