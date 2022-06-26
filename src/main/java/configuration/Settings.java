package configuration;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface Settings extends Config{

    @Key(value = "isRemoteRun")
    String getIsRemoteRun();

    @Key(value = "browser")
    String getBrowser();

    @Key(value = "gridUrl")
    String getGridUrl();

    @Key(value = "chromeDriverLocation")
    String getChromeDriverLocation();

    @Key(value = "edgeDriverLocation")
    String getEdgeDriverLocation();

    @Key(value = "googleUrl")
    String getGoogleUrl();

}
