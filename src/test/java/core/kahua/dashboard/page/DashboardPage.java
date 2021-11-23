package core.kahua.dashboard.page;

import general.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardPage extends PageObject {
    private String userAndProjectContentElementXpath;

    public DashboardPage() {
        setUserAndProjectContentElementXpath("//div[@aria-label='Shell_AccountHeader']/descendant::p");
        waitForElementsAndSet(By.xpath(getUserAndProjectContentElementXpath()));
    }

    public boolean get_user_and_project() {
        try {
            for (WebElement element : getElements()) {
                setElement(element);
                print(getElement().getText());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserAndProjectContentElementXpath() {
        return userAndProjectContentElementXpath;
    }

    public void setUserAndProjectContentElementXpath(String userAndProjectContentElementXpath) {
        this.userAndProjectContentElementXpath = userAndProjectContentElementXpath;
    }
}
