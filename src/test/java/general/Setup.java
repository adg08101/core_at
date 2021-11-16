package general;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class Setup {
    private static WebDriver driver;
    private static HashMap<String, Object> store;
    private static Actions actions;
    private static WaitingObject waitingObject;
    private static String defaultURL;
    private static JavascriptExecutor jsExecutor;
    public static Map<String, Object> timeouts;

    @Before
    public void InitSetup() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions options = new ChromeOptions();
        timeouts = new HashMap<String, Object>();
        timeouts.put("implicit", 50);
        timeouts.put("pageLoad", 5000000);
        timeouts.put("script", 300000);
        options.setCapability("timeouts", timeouts);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        initObject();
    }

    private static void initObject() {
        waitingObject = new WaitingObject(driver);
        actions = new Actions(driver);
        store = new HashMap<String, Object>();
        setJsExecutor((JavascriptExecutor) driver);
        loadDefaultProperties();
    }

    public static Map<String, Object> getTimeouts() {
        return timeouts;
    }

    public static Object executeScript(String script, Object... arg) {
        return getJsExecutor().executeScript(script,arg);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Actions getActions() {
        return actions;
    }

    /**
     *
     * @param key
     * @return
     */
    public static Object getValueStore(String key) {
        return store.get(key);
    }

    /**
     *
     * @return Return an instance of wait.
     */
    public static WaitingObject getWait() {
        return waitingObject;
    }

    /**
     *
     * @param key
     * @param value
     */
    public static void setKeyValueStore(String key, Object value) {
        store.put(key, value);
    }

    /**
     * Open new url
     *
     * @param url
     */
    public static void openUrl(String url) {
        driver.get(url);
    }

    private static void loadDefaultProperties() {
        InputStream input = Setup.class.getResourceAsStream("/defaultproperties.properties");

        Properties pop = new Properties();
        try {
            pop.load(input);
        } catch (java.io.IOException e) { }

        setKeyValueStore("defaultProperties", pop);
        System.setProperty("defaultURL", (String) pop.get("default.url"));
        setKeyValueStore("default_url", System.getProperty("defaultURL"));
    }

    public static JavascriptExecutor getJsExecutor() {
        return jsExecutor;
    }

    public static void setJsExecutor(JavascriptExecutor jsExecutor) {
        Setup.jsExecutor = jsExecutor;
    }

    @After
    public void close() {
        getWait().thread(1500);
        driver.close();
    }
}
