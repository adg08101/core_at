package general;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageObject {
    private String urlPath;
    private WebDriverWait wait;
    private int waitTime;
    private Faker faker;
    private String user_name;
    private String user_password;
    private WebElement element;
    private List<WebElement> elements;
    private String view;

    public PageObject() {
        setFaker(new Faker());
        PageFactory.initElements(this.getDriver(), this);
        setWaitTime((Integer) Setup.getConfigProperties().getProperties().get(Property.TIMEOUT_IMPLICIT_VALUE));
        setWait(new WebDriverWait(this.getDriver(), getWaitTime()));
    }

    public boolean login(LoginType loginType, String username, String password,
                      By usernameLocator, By passwordLocator, String[] submitElements) {
        try {
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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean logoff(String[] logoffItems) {
        try {
            for (String element : logoffItems)
                clickOnItem(By.xpath(element));
            return true;
        } catch (Exception e) {
            print(e.getMessage());
            return false;
        }
    }

    public void waitForElementAndSet(By elementLocator) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        setElement(getWebElement(elementLocator));
    }

    public void waitForElementsAndSet(By elementLocator) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        setElements(getWebElements(elementLocator));
    }

    public void clickOnItem(By elementLocator) {
        waitForElementAndSet(elementLocator);
        Setup.getActions().click(getElement()).build().perform();
    }

    public void sendKeysToInput(Object key, By elementLocator) {
        try {
            waitForElementAndSet(elementLocator);
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

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getAppPrefix() {
        return System.getProperties().getProperty((String) Setup.getConfigProperties().getProperties().
                get(Property.STR_APP_PREFIX));
    }

    public void setAppPrefix(String appPrefix) {
        System.setProperty((String) Setup.getConfigProperties().getProperties().get(Property.
                STR_APP_PREFIX), appPrefix);
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

    public void openURL(String app) {
        setUrlPath((String) Setup.getPropertyFromKey(Property.valueOf(app + "_URL")));
        Setup.openUrl(getUrlPath());

        print(getUrlPath());
        print(getFaker().number().randomNumber());
    }

    public WebElement getWebElement(By by) {
        return this.getDriver().findElement(by);
    }

    public List<WebElement> getWebElements(By by) {
        return this.getDriver().findElements(by);
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

    public List<WebElement> getElements() {
        return elements;
    }

    public void setElements(List<WebElement> elements) {
        this.elements = elements;
    }
}
