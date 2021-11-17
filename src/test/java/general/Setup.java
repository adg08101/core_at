package general;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class Setup {
    private static WebDriver driver;
    private static HashMap<String, Object> store;
    private static ChromeOptions options;
    private static ConfigProperties configProperties;
    private static WaitingObject waitingObject;
    private static JavascriptExecutor jsExecutor;
    private static Map<String, Object> timeouts;
    private static InputStream input;
    private static Properties properties;

    public static void setStore(HashMap<String, Object> store) {
        Setup.store = store;
    }

    public static InputStream getInput() {
        return input;
    }

    public static void setInput(InputStream input) {
        Setup.input = input;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        Setup.properties = properties;
    }

    @Before
    public void InitSetup() {
        try {
            setConfigProperties(new ConfigProperties());
            System.setProperty((String) getConfigProperties().getProperties().get(Property.WEBDRIVER_CHROME_DRIVER),
                    System.getenv((String) getConfigProperties().getProperties().get(Property.CHROME_DRIVER)));
            System.setProperty((String) getConfigProperties().getProperties().get(Property.
                    WEBDRIVER_CHROME_SILENTOUTPUT), (String) getConfigProperties().getProperties().
                    get(Property.STRING_TRUE));
            setOptions(new ChromeOptions());
            setTimeouts(new HashMap<>());
            getTimeouts().put((String) getConfigProperties().getProperties().get(Property.TIMEOUT_IMPLICIT_KEY),
                    getConfigProperties().getProperties().get(Property.TIMEOUT_IMPLICIT_VALUE));
            getTimeouts().put((String) getConfigProperties().getProperties().get(Property.TIMEOUT_PAGELOAD_KEY),
                    getConfigProperties().getProperties().get(Property.TIMEOUT_PAGELOAD_VALUE));
            getTimeouts().put((String) getConfigProperties().getProperties().get(Property.TIMEOUT_SCRIPT_KEY),
                    getConfigProperties().getProperties().get(Property.TIMEOUT_SCRIPT_VALUE));
            getOptions().setCapability((String) getConfigProperties().getProperties().get(Property.STRING_TIMEOUTS),
                    getTimeouts());
            setDriver(new ChromeDriver(getOptions()));
            getDriver().manage().window().maximize();
            initObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initObject() {
        waitingObject = new WaitingObject(getDriver());
        setStore(new HashMap<>());
        setJsExecutor((JavascriptExecutor) getDriver());
        loadDefaultProperties();
    }

    private void loadDefaultProperties() {
        setInput(Setup.class.getResourceAsStream((String) getConfigProperties().
                getProperties().get(Property.DEFAULT_PROPERTIES_FILE_PATH)));

        setProperties(new Properties());

        try {
            getProperties().load(getInput());
        } catch (java.io.IOException ignored) { }

        setKeyValueStore((String) getConfigProperties().getProperties().get(
                Property.STRING_DEFAULT_PROPERTIES), getProperties());
    }

    public static ChromeOptions getOptions() {
        return options;
    }

    public static void setOptions(ChromeOptions options) {
        Setup.options = options;
    }

    public static void setTimeouts(Map<String, Object> timeouts) {
        Setup.timeouts = timeouts;
    }

    public static void setDriver(WebDriver driver) {
        Setup.driver = driver;
    }

    public static Map<String, Object> getTimeouts() {
        return timeouts;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    /**
     *
     * @return Return an instance of wait.
     */
    public static WaitingObject getWait() {
        return waitingObject;
    }

    public static HashMap<String, Object> getStore() {
        return store;
    }

    /**
     *
     * @param key Key for adding content to Store
     * @param value Value for key on store
     */
    public static void setKeyValueStore(String key, Object value) {
        getStore().put(key, value);
    }

    /**
     * Open new url
     *
     * @param url Url to open using driver
     */
    public static void openUrl(String url) {
        getDriver().get(url);
    }

    public static void setJsExecutor(JavascriptExecutor jsExecutor) {
        Setup.jsExecutor = jsExecutor;
    }

    public static JavascriptExecutor getJsExecutor() {
        return jsExecutor;
    }

    public static ConfigProperties getConfigProperties() {
        return configProperties;
    }

    public static void setConfigProperties(ConfigProperties configProperties) {
        Setup.configProperties = configProperties;
    }

    @After
    public void close() {
        getWait().thread((Long) getConfigProperties().getProperties().get(Property.INT_SHORT_TIME));
        getDriver().close();
    }
}
