package general;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

import java.util.Locale;

public class GeneralSteps extends PageObject {

    private String view;
    private String userName;
    private String password;
    private LoginType loginType;
    private String userNameField;
    private String passwordField;
    private String tempSubmitItems;
    private String[] submitItems;
    private String appPrefix;

    public GeneralSteps() {
        super();
    }

    @Given("The user is in {string} view")
    public void the_user_is_in_view(String view) {
        setView(view);
        setAppPrefix(getView().substring(0, getView().indexOf(" ")).toUpperCase(Locale.ROOT));

        try {
            openURL(getAppPrefix());
        } catch (Exception e) { print(e.getMessage()); }
    }

    @Then("The user LogsIn with {string} and {string}")
    public void the_user_logsin_with_username_and_password(String str0, String str1) {
        //TODO load config properties here
        //TODO Everything changes from here on

        setUserName(str0);
        setPassword(str1);

        setLoginType(LoginType.valueOf((String) Setup.getPropertyFromKey(Property.valueOf(
                getAppPrefix() + "_LOGIN_TYPE"))));
        setUserNameField((String) Setup.getPropertyFromKey(Property.valueOf(
                getAppPrefix() + "_USERNAME_FIELD")));
        setPasswordField((String) Setup.getPropertyFromKey(Property.valueOf(
                getAppPrefix() + "_PASSWORD_FIELD")));
        setTempSubmitItems((String)Setup.getPropertyFromKey(Property.valueOf(
                getAppPrefix() + "_SUBMIT_ELEMENTS")));
        setSubmitItems(getTempSubmitItems().split(","));

        login(getLoginType(), getUserName(), getPassword(), By.xpath(getUserNameField()), By.xpath(getPasswordField()),
                getSubmitItems());
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

    public String[] getSubmitItems() {
        return submitItems;
    }

    public void setSubmitItems(String[] submitItems) {
        this.submitItems = submitItems;
    }

    public String getTempSubmitItems() {
        return tempSubmitItems;
    }

    public void setTempSubmitItems(String tempSubmitItems) {
        this.tempSubmitItems = tempSubmitItems;
    }

    public String getAppPrefix() {
        return appPrefix;
    }

    public void setAppPrefix(String appPrefix) {
        this.appPrefix = appPrefix;
    }
}
