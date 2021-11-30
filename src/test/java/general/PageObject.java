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
    private String userName;
    private String password;
    private LoginType loginType;

    public PageObject() {
        setFaker(new Faker());
        PageFactory.initElements(this.getDriver(), this);
        setWaitTime((Integer) Setup.getConfigProperties().getProperties().get(Property.TIMEOUT_IMPLICIT_VALUE));
        setWait(new WebDriverWait(this.getDriver(), getWaitTime()));
    }

    public boolean logoff(String[] logoffItems) {
        Setup.waitTime(3);
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

    public void waitForElementsToBeClickable(By elementLocator) {
        getWait().until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

    public void clickOnItem(By elementLocator) {
        waitForElementAndSet(elementLocator);
        waitForElementsToBeClickable(elementLocator);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public void setPassword(String password) {
        this.password = password;
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
