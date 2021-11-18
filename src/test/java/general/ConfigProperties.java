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
        getProperties().put(Property.GOHEAVY_URL, "goheavy_url");
        getProperties().put(Property.STRING_DEFAULT_PROPERTIES, "defaultProperties");
        getProperties().put(Property.STRING_USER_NAME, "user_name");
        getProperties().put(Property.STRING_USER_PASSWORD, "user_password");
        getProperties().put(Property.KAHUA_URL, "kahua_url");
        getProperties().put(Property.KAHUA_LOGIN_TYPE, "kahua_login_type");
        getProperties().put(Property.KAHUA_USERNAME_FIELD, "kahua_username_field");
        getProperties().put(Property.KAHUA_PASSWORD_FIELD, "kahua_password_field");
        getProperties().put(Property.KAHUA_SUBMIT_ELEMENTS, "kahua_submit_elements");
        getProperties().put(Property.GOHEAVY_LOGIN_TYPE, "goheavy_login_type");
        getProperties().put(Property.GOHEAVY_USERNAME_FIELD, "goheavy_username_field");
        getProperties().put(Property.GOHEAVY_PASSWORD_FIELD, "goheavy_password_field");
        getProperties().put(Property.GOHEAVY_SUBMIT_ELEMENTS, "goheavy_submit_elements");
    }

    public HashMap<Object, Object> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<Object, Object> properties) {
        this.properties = properties;
    }
}
