package configuration;

import org.openqa.selenium.WebDriver;

import static configuration.TestRunProperties.getBrowserFromProperties;
import static configuration.TestRunProperties.getIsRemoteRunFromProperties;

public class DriverManager {
    private static WebDriver driver;
    public static WebDriver getWebDriver() {
        if (driver == null) {
            driver = new BrowserFactory(getBrowserFromProperties(), getIsRemoteRunFromProperties()).getBrowser();
        }
        return driver;
    }
    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
