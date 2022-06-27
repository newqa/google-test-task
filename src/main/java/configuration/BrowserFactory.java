package configuration;

import lombok.AllArgsConstructor;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static configuration.BrowserOptions.getChromeOptions;
import static configuration.BrowserOptions.getEdgeOptions;

@AllArgsConstructor
public class BrowserFactory {

    public static Settings settings = ConfigFactory.create(Settings.class, System.getProperties());
    private static final String UNKNOWN_BROWSER_MESSAGE = "Unknown browser type.";
    private BrowserType browser;
    private boolean isRemoteRun;

    public WebDriver getBrowser() {
        if (isRemoteRun) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            switch (browser) {
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    desiredCapabilities.merge(chromeOptions);
                    return getRemoteWebDriver(desiredCapabilities);
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    desiredCapabilities.merge(edgeOptions);
                    return getRemoteWebDriver(desiredCapabilities);
                default:
                    throw new IllegalStateException(UNKNOWN_BROWSER_MESSAGE);
            }
        } else {
            switch (browser) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", settings.getChromeDriverLocation());
                    return new ChromeDriver(getChromeOptions());
                case EDGE:
                    System.setProperty("webdriver.edge.driver", settings.getEdgeDriverLocation());
                    return new EdgeDriver(getEdgeOptions());
                default:
                    throw new IllegalStateException(UNKNOWN_BROWSER_MESSAGE);
            }
        }
    }

    private WebDriver getRemoteWebDriver(DesiredCapabilities desiredCapabilities) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            remoteWebDriver = new RemoteWebDriver(new URL(settings.getGridUrl()), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create RemoteWebDriver due to: " + e.getMessage());
        }
        return remoteWebDriver;
    }

}

