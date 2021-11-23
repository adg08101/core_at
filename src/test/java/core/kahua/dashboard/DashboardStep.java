package core.kahua.dashboard;

import core.kahua.dashboard.page.DashboardPage;
import org.junit.Assert;

public class DashboardStep {
    private DashboardPage dashboardPage;

    public DashboardStep() {
        dashboardPage = new DashboardPage();
    }

    public void save_user_and_project() {
        Assert.assertTrue(dashboardPage.get_user_and_project());
    }
}
