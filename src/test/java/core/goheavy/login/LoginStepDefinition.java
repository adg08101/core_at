package core.goheavy.login;

import io.cucumber.java.en.Then;

public class LoginStepDefinition {
    LoginStep loginStep;

    public LoginStepDefinition() {
        loginStep = new LoginStep();
    }

    @Then("The user LogsIn with {string} and {string}")
    public void the_user_logsin_with_username_and_password(String str0, String str1) {
        try {
            loginStep.the_user_logsin_with_username_and_password(str0, str1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
