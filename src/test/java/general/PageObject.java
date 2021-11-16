package general;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

public class PageObject {
    private WebDriver driver;
    protected String urlPath;
    private WebDriverWait wait;
    private int waitTime;
    public Faker faker;

    public PageObject() {
        this.setDriver(Setup.getDriver());
        this.setFaker(new Faker());
        PageFactory.initElements(this.getDriver(), this);
        setWaitTime(20);
        setWait(new WebDriverWait(this.getDriver(), this.getWaitTime()));
    }

    public void setImage(WebElement element, Object object) {
        //TODO stImage
    }

    public void setImageImproved(String title, Object object) {
        //TODO setImageImproved
    }

    public void sendDataToInputImproved(String labelText, String data, Keys key, InputType type, boolean scroll, String form,
                                        int y_pos) {
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
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void openURL() {
        Setup.openUrl(System.getProperty("defaultURL"));
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
}
