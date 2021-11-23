package general;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Locale;

public class GeneralSteps extends PageObject {

    private String view;
    private String userName;
    private String password;
    private LoginType loginType;
    private String userNameField;
    private String passwordField;
    private String tempLoginItems;
    private String[] loginItems;
    private String appPrefix;
    private String tempLogoffItems;
    private String[] logoffItems;

    public GeneralSteps() {
        super();
    }

    @Given("The user is in {string} view")
    public void the_user_is_in_view(String view) {
        try {
            setView(view);
            setAppPrefix(getView().substring(0, getView().indexOf(" ")).
                    toUpperCase(Locale.ROOT));

            openURL(getAppPrefix());
        } catch (Exception e) { print(e.getMessage()); }
    }

    @Then("The user LogsIn with {string} and {string}")
    public void the_user_logsin_with_username_and_password(String str0, String str1) {
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

            Assert.assertTrue(login(getLoginType(), getUserName(), getPassword(),
                    By.xpath(getUserNameField()), By.xpath(getPasswordField()), getLoginItems()));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Then("The user LogsOff")
    public void the_user_logsoff() {
        try {
            setTempLogoffItems((String)Setup.getPropertyFromKey(Property.valueOf(
                    getAppPrefix() + Setup.getConfigProperties().getProperties().get(Property.STR_LOGOFF_ELEMENTS))));
            setLogoffItems(getTempLogoffItems().split((String) Setup.getConfigProperties().getProperties().get(
                    Property.CHAR_COMMA)));

            Assert.assertTrue(logoff(getLogoffItems()));
        } catch (Exception e) {
            e.printStackTrace();
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

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getUserNameField() {
        return userNameField;
    }

    public void setUserNameField(String userNameField) {
        this.userNameField = userNameField;
    }

    public String getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(String passwordField) {
        this.passwordField = passwordField;
    }

    public String[] getLoginItems() {
        return loginItems;
    }

    public void setLoginItems(String[] loginItems) {
        this.loginItems = loginItems;
    }

    public String getTempLoginItems() {
        return tempLoginItems;
    }

    public void setTempLoginItems(String tempLoginItems) {
        this.tempLoginItems = tempLoginItems;
    }

    public String getAppPrefix() {
        return appPrefix;
    }

    public void setAppPrefix(String appPrefix) {
        this.appPrefix = appPrefix;
    }

    public String getTempLogoffItems() {
        return tempLogoffItems;
    }

    public void setTempLogoffItems(String tempLogoffItems) {
        this.tempLogoffItems = tempLogoffItems;
    }

    public String[] getLogoffItems() {
        return logoffItems;
    }

    public void setLogoffItems(String[] logoffItems) {
        this.logoffItems = logoffItems;
    }
}
