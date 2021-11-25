package general;

import core.goheavy.login.LoginStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Locale;

public class GeneralSteps extends PageObject {
    private String tempLogoffItems;
    private String[] logoffItems;

    public GeneralSteps() {
        super();
    }

    @Given("The user is in {string} view")
    public void the_user_is_in_view(String view) {
        if (view.toLowerCase(Locale.ROOT).contains("login")) {
            LoginStep loginStep = new LoginStep();
            loginStep.the_user_is_in_login_view(view);
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
