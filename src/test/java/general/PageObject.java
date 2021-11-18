package general;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashMap;
import java.util.List;

public class PageObject {
    private String urlPath;
    private WebDriverWait wait;
    private int waitTime;
    private Faker faker;
    private String user_name;
    private String user_password;
    private int x_pos;
    private int y_pos;
    private String view;
    private WebElement element;

    public PageObject() {
        this.setFaker(new Faker());
        PageFactory.initElements(this.getDriver(), this);
        setWaitTime((Integer) Setup.getConfigProperties().getProperties().get(Property.TIMEOUT_PAGELOAD_VALUE));
        setWait(new WebDriverWait(this.getDriver(), getWaitTime()));
    }

    public String getDefaultProperties(String key) {
        return Setup.getProperties().getProperty(key);
    }

    public void login(LoginType loginType, String username, String password,
                      By usernameLocator, By passwordLocator, String[] submitElements) {
        //TODO login
        System.setProperty((String) Setup.getConfigProperties().getProperties().get(Property.
                STRING_USER_NAME), getDefaultProperties(username).equals(null) ?
                username : getDefaultProperties(username));

        System.setProperty((String) Setup.getConfigProperties().getProperties().get(Property.
                STRING_USER_PASSWORD), getDefaultProperties(password).equals(null) ?
                password : getDefaultProperties(password));

        setUser_name(System.getProperties().getProperty((String) Setup.getConfigProperties().getProperties().
                get(Property.STRING_USER_NAME)));

        setUser_password(System.getProperties().getProperty((String) Setup.getConfigProperties().
                getProperties().get(Property.STRING_USER_PASSWORD)));

        switch (loginType) {
            case USER_AND_PASS:
                //TODO User and Pass Login Code
                sendKeysToInput(getUser_name(), usernameLocator);
                sendKeysToInput(getUser_password(), passwordLocator);
                sendKeysToInput(Keys.RETURN, By.xpath(submitElements[0]));
                break;
            case USER_THEN_PASS:
                //TODO User then Pass Login Code
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

            if (getUser_name().equals(key) && getView().equals("Kahua Login")) {
                setX_pos(getElement().getLocation().getX());
                setY_pos(getElement().getLocation().getY());
            }

            Setup.getActions().moveToElement(getElement()).build().perform();

            if (key instanceof Keys)
                Setup.getActions().sendKeys(getElement(), (Keys) key).build().perform();
            else {
                getElement().clear();
                Setup.getActions().sendKeys(getElement(), (String) key).build().perform();
            }
        } catch (Exception e) {
            getElement().sendKeys(getUser_password());
        }
    }

    public void setImage(WebElement element, Object object) {
        //TODO setImage
    }

    public void setImageImproved(String title, Object object) {
        //TODO setImageImproved
    }

    public void sendDataToInputImproved(String labelText, String data, Keys key, InputType type, boolean scroll,
                                        String form, int y_pos) {
        //TODO sendDataToInputImproved
    }

    public void print(Object string) {
        System.out.println(string);
    }

    public void pl(Object string) {
        this.print(string);
    }

    public void submitForm(String formId) {
        //TODO submitForm
    }

    public void formScrollImproved(String form, int yScrollBy) {
        //TODO formScrollImproved
    }

    public void killEvent() {
        System.exit(0);
    }

    public Faker getFaker() {
        return faker;
    }

    void setFaker(Faker faker) {
        this.faker = faker;
    }

    public WebDriver getDriver() {
        return Setup.getDriver();
    }

    public void openURL(String view) {
        setView(view);
        if (view.equals("GoHeavy Login"))
            Setup.openUrl(Setup.getProperties().getProperty((String) Setup.getConfigProperties().getProperties().
                    get(Property.STRING_DEFAULT_URL)));
        else if (view.equals("Kahua Login")) {
            Setup.openUrl(Setup.getProperties().getProperty((String) Setup.getConfigProperties().getProperties().
                    get(Property.KAHUA_URL)));
        }
    }

    protected WebElement getWebElement(By by) {
        return this.getDriver().findElement(by);
    }

    protected List<WebElement> getWebElements(By by) {
        return this.getDriver().findElements(by);
    }

    protected void clickOnWebElement(By by) {
        //TODO clickOnWebElement
    }

    public String getCurrentUrl() {
        return this.getDriver().getCurrentUrl();
    }

    public String getPagePath() {
        return this.urlPath;
    }

    public void scroll(String scrollElementxpath, By targetElementxpath) {
        //TODO scroll
    }

    public HashMap<String, WebElement> getMenu(By by) {
        //TODO getMenu
        return null;
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

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
}
