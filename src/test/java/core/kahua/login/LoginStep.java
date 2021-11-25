package core.kahua.login;

import core.kahua.login.page.LoginPage;
import org.junit.Assert;

public class LoginStep {
    private final LoginPage loginPage;

    public LoginStep() {
        loginPage = new LoginPage();
    }

    public void the_user_is_in_login_view(String view) {
        Assert.assertNotNull(loginPage.the_user_is_in_login_page(view));
    }
}
