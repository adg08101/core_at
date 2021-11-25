package core.goheavy.dashboard;

import io.cucumber.java.en.Then;

public class DashboardStepDefinition {
    private final DashboardStep dashboardStep;

    public DashboardStepDefinition()  {
        dashboardStep = new DashboardStep();
    }

    @Then("The system saves user and project values")
    public void the_system_saves_user_and_project_values() {
        try {
            dashboardStep.save_user_and_project();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
