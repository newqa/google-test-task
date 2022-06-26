package configuration;

import org.aeonbits.owner.ConfigFactory;

public class TestRunProperties {

    public static Settings settings = ConfigFactory.create(Settings.class, System.getProperties());

    public static BrowserType getBrowserFromProperties() {
        return BrowserType.valueOf(settings.getBrowser().toUpperCase());
    }
    public static boolean getIsRemoteRunFromProperties() {
        return Boolean.parseBoolean(settings.getIsRemoteRun());
    }
}
