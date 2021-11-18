package general;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;

public class GeneralSteps extends PageObject {

    public GeneralSteps() {
        super();
    }

    @Given("The user is in {string} view")
    public void the_user_is_in_view(String view) {
        try {
            openURL(view);
        } catch (Exception e) { print(e.getMessage()); }
    }

    @Then("The user LogsIn with {string} and {string} on {string} and {string} on {string} for {string}")
    public void
    the_user_logsin_with_logintype_and_username_on_usernamefield_and_password_on_passwordfield_for_submitelement
            (String str0, String str1, String str2, String str3, String str4, String str5) {

        String[] submitItems = str5.split(",");

        if (str0.equals(String.valueOf(LoginType.USER_AND_PASS)))
            login(LoginType.USER_AND_PASS, str1, str3, By.xpath(str2), By.xpath(str4), submitItems);
        else if (str0.equals(String.valueOf(LoginType.USER_THEN_PASS)))
            login(LoginType.USER_THEN_PASS, str1, str3, By.xpath(str2), By.xpath(str4), submitItems);
    }
}
