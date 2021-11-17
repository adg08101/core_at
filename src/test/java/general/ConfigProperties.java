package general;

import java.util.HashMap;

public class ConfigProperties {

    private HashMap<Object, Object> properties;

    public ConfigProperties() {
        setProperties(new HashMap<>());
        getProperties().put(Property.WEBDRIVER_CHROME_DRIVER, "webdriver.chrome.driver");
        getProperties().put(Property.WEBDRIVER_CHROME_SILENTOUTPUT, "webdriver.chrome.silentOutput");
        getProperties().put(Property.CHROME_DRIVER, "CHROME_DRIVER");
        getProperties().put(Property.STRING_TRUE, "true");
        getProperties().put(Property.BOOL_TRUE, true);
        getProperties().put(Property.TIMEOUT_IMPLICIT_KEY, "implicit");
        getProperties().put(Property.TIMEOUT_IMPLICIT_VALUE, 50);
        getProperties().put(Property.TIMEOUT_PAGELOAD_KEY, "pageLoad");
        getProperties().put(Property.TIMEOUT_PAGELOAD_VALUE, 5000000);
        getProperties().put(Property.TIMEOUT_SCRIPT_KEY, "script");
        getProperties().put(Property.TIMEOUT_SCRIPT_VALUE, 300000);
        getProperties().put(Property.STRING_TIMEOUTS, "timeouts");
        getProperties().put(Property.INT_SHORT_TIME, Long.parseLong("1500"));
        getProperties().put(Property.DEFAULT_PROPERTIES_FILE_PATH, "/defaultProperties.properties");
        getProperties().put(Property.STRING_DEFAULT_URL, "default_url");
        getProperties().put(Property.STRING_DEFAULT_PROPERTIES, "defaultProperties");
    }

    public HashMap<Object, Object> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<Object, Object> properties) {
        this.properties = properties;
    }
}
