package core.kahua.login.page;

import general.LoginType;
import general.PageObject;
import general.Property;
import general.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class LoginPage extends PageObject {
    private String kahuaLogoXpath;
    private String userNameField;
    private String passwordField;
    private String tempLoginItems;
    private String[] loginItems;

    public LoginPage() {
        super();
        setKahuaLogoXpath("//img[contains(@src, 'kahua_logo_full')]");
    }

    public WebElement the_user_is_in_login_page(String view) {
        try {
            setView(view);
            setAppPrefix(getView().substring((Integer) Setup.getConfigProperties().getProperties().get(
                    Property.INT_ZERO), getView().indexOf((String) Setup.getConfigProperties().getProperties().get(
                    Property.CHAR_SPACE))).toUpperCase(Locale.ROOT));

            openURL(getAppPrefix());
            waitForElementAndSet(By.xpath(getKahuaLogoXpath()));
            return getElement();
        } catch (Exception e) {
            print(e.getMessage());
            return null;
        }
    }

    public boolean the_user_logsin_with_username_and_password(String str0, String str1) {
        try {
            setUserName(str0);
            setPassword(str1);

            setLoginType(LoginType.valueOf((String) Setup.getPropertyFromKey(Property.valueOf(
                    getAppPrefix() + Setup.getConfigProperties().getProperties().get(Property.STR_LOGIN_TYPE)))));
            setUserNameField((String) Setup.getPropertyFromKey(Property.valueOf(
                    getAppPrefix() + Setup.getConfigProperties().getProperties().get(Property.STR_USERNAME_FIELD))));
            setPasswordField((String) Setup.getPropertyFromKey(Property.valueOf(
                    getAppPrefix() + Setup.getConfigProperties().getProperties().get(Property.STR_PASSWORD_FIELD))));
            setTempLoginItems((String)Setup.getPropertyFromKey(Property.valueOf(
                    getAppPrefix() + Setup.getConfigProperties().getProperties().get(Property.STR_LOGIN_ELEMENTS))));
            setLoginItems(getTempLoginItems().split((String) Setup.getConfigProperties().getProperties().get(
                    Property.CHAR_COMMA)));

            return login(getLoginType(), getUserName(), getPassword(), By.xpath(getUserNameField()),
                    By.xpath(getPasswordField()), getLoginItems());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
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
                    sendKeysToInput(Keys.RETURN, By.xpath(submitElements[(Integer) Setup.getConfigProperties().
                            getProperties().get(Property.INT_ZERO)]));
                    break;
                case USER_THEN_PASS:
                    sendKeysToInput(getUser_name(), usernameLocator);
                    sendKeysToInput(Keys.RETURN, By.xpath(submitElements[(Integer) Setup.getConfigProperties().
                            getProperties().get(Property.INT_ZERO)]));
                    sendKeysToInput(getUser_password(), passwordLocator);
                    sendKeysToInput(Keys.RETURN, By.xpath(submitElements[(Integer) Setup.getConfigProperties().
                            getProperties().get(Property.INT_ONE)]));
                    break;
                case PASS_ONLY:
                    sendKeysToInput(getUser_password(), passwordLocator);
                    sendKeysToInput(Keys.RETURN, By.xpath(submitElements[(Integer) Setup.getConfigProperties().
                            getProperties().get(Property.INT_ONE)]));
                    break;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[] getLoginItems() {
        return loginItems;
    }

    public void setLoginItems(String[] loginItems) {
        this.loginItems = loginItems;
    }

    public String getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(String passwordField) {
        this.passwordField = passwordField;
    }

    public String getUserNameField() {
        return userNameField;
    }

    public void setUserNameField(String userNameField) {
        this.userNameField = userNameField;
    }

    public String getTempLoginItems() {
        return tempLoginItems;
    }

    public void setTempLoginItems(String tempLoginItems) {
        this.tempLoginItems = tempLoginItems;
    }

    public String getKahuaLogoXpath() {
        return kahuaLogoXpath;
    }

    public void setKahuaLogoXpath(String kahuaLogoXpath) {
        this.kahuaLogoXpath = kahuaLogoXpath;
    }
}
