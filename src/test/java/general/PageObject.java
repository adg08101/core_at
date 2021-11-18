package general;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    private String urlPath;
    private WebDriverWait wait;
    private int waitTime;
    private Faker faker;
    private String user_name;
    private String user_password;
    private WebElement element;

    public PageObject() {
        setFaker(new Faker());
        PageFactory.initElements(this.getDriver(), this);
        setWaitTime((Integer) Setup.getConfigProperties().getProperties().get(Property.TIMEOUT_PAGELOAD_VALUE));
        setWait(new WebDriverWait(this.getDriver(), getWaitTime()));
    }

    public void login(LoginType loginType, String username, String password,
                      By usernameLocator, By passwordLocator, String[] submitElements) {

        System.setProperty((String) Setup.getConfigProperties().getProperties().get(Property.
                STRING_USER_NAME), Setup.getProperties().getProperty(username) == null ?
                username : Setup.getProperties().getProperty(username));

        System.setProperty((String) Setup.getConfigProperties().getProperties().get(Property.
                STRING_USER_PASSWORD), Setup.getProperties().getProperty(password) == null ?
                password : Setup.getProperties().getProperty(password));

        setUser_name(System.getProperties().getProperty((String) Setup.getConfigProperties().getProperties().
                get(Property.STRING_USER_NAME)));

        setUser_password(System.getProperties().getProperty((String) Setup.getConfigProperties().
                getProperties().get(Property.STRING_USER_PASSWORD)));

        switch (loginType) {
            case USER_AND_PASS:
                sendKeysToInput(getUser_name(), usernameLocator);
                sendKeysToInput(getUser_password(), passwordLocator);
                sendKeysToInput(Keys.RETURN, By.xpath(submitElements[0]));
                break;
            case USER_THEN_PASS:
                sendKeysToInput(getUser_name(), usernameLocator);
                sendKeysToInput(Keys.RETURN, By.xpath(submitElements[0]));
                sendKeysToInput(getUser_password(), passwordLocator);
                sendKeysToInput(Keys.RETURN, By.xpath(submitElements[1]));
                break;
            case PASS_ONLY:
                //Todo Pass only code
                break;
        }
    }

    public void sendKeysToInput(Object key, By elementLocator) {
        try {
            getWait().until(ExpectedConditions.presenceOfElementLocated(elementLocator));
            setElement(getWebElement(elementLocator));

            Setup.getActions().moveToElement(getElement()).build().perform();

            if (key instanceof Keys)
                Setup.getActions().sendKeys(getElement(), (Keys) key).build().perform();
            else {
                getElement().clear();
                Setup.getActions().sendKeys(getElement(), (String) key).build().perform();
            }
        } catch (Exception e) {
            try {
                Setup.getActions().sendKeys(getUser_password()).build().perform();
                Setup.getActions().sendKeys(Keys.RETURN).build().perform();
                print(e.getMessage());
            } catch (Exception i) { print(i.getMessage()); }
        }
    }

    public void print(Object string) {
        System.out.println(string);
    }

    void setFaker(Faker faker) {
        this.faker = faker;
    }

    public WebDriver getDriver() {
        return Setup.getDriver();
    }

    public void openURL(String view) {
        if (view.equals("GoHeavy Login")) {
            setUrlPath(Setup.getProperties().getProperty((String) Setup.getConfigProperties().getProperties().
                    get(Property.STRING_DEFAULT_URL)));
            Setup.openUrl(getUrlPath());
        }
        else if (view.equals("Kahua Login")) {
            setUrlPath(Setup.getProperties().getProperty((String) Setup.getConfigProperties().getProperties().
                    get(Property.KAHUA_URL)));
            Setup.openUrl(getUrlPath());
        }
        print(getUrlPath());
        print(getFaker().number().randomNumber());
    }

    public WebElement getWebElement(By by) {
        return this.getDriver().findElement(by);
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public Faker getFaker() {
        return faker;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
