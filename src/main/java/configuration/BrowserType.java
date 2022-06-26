package configuration;

public enum BrowserType {

    CHROME("chrome"),
    EDGE("edge");

    private final String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }
}
