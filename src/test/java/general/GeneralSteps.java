package general;

import io.cucumber.java.en.Given;

public class GeneralSteps extends PageObject {

    public GeneralSteps() {
        super();
    }

    @Given("The user is in {string} view.")
    public void the_user_is_in_view(String view) {
        try {
            if (view.equals("Google Search")) {
                openURL();
            }
        } catch (Exception e) { }
    }
}
