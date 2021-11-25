package core.goheavy.dashboard.page;

import general.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardPage extends PageObject {
    private String userAndEmailElementXpath;

    public DashboardPage() {
        setUserAndEmailElementXpathElementXpath("//div[contains(@class, 'AvatarText')]");
        waitForElementAndSet(By.xpath(getUserAndEmailElementXpath()));
    }

    public boolean get_user_and_project() {
        try {
            print(getElement().findElement(By.tagName("b")).getText());
            print(getElement().findElement(By.tagName("small")).getText());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserAndEmailElementXpath() {
        return userAndEmailElementXpath;
    }

    public void setUserAndEmailElementXpathElementXpath(String userAndProjectContentElementXpath) {
        this.userAndEmailElementXpath = userAndProjectContentElementXpath;
    }
}
