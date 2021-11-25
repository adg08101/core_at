package core.kahua.login.page;

import general.PageObject;
import general.Property;
import general.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class LoginPage extends PageObject {
    private String kahuaLogoXpath;

    public LoginPage() {
        super();
        setKahuaLogoXpath("//img[contains(@src, 'kahua_logo_full')]");
    }

    public WebElement the_user_is_in_login_page(String view) {
        try {
            setView(view);
            setAppPrefix(getView().substring((Integer) Setup.getConfigProperties().getProperties().get(
                    Property.INT_ZERO), getView().indexOf((String) Setup.getConfigProperties().getProperties().get(
                    Property.CHAR_SPACE))).toUpperCase(Locale.ROOT));

            openURL(getAppPrefix());
            waitForElementAndSet(By.xpath(getKahuaLogoXpath()));
            return getElement();
        } catch (Exception e) {
            print(e.getMessage());
            return null;
        }
    }

    public String getKahuaLogoXpath() {
        return kahuaLogoXpath;
    }

    public void setKahuaLogoXpath(String kahuaLogoXpath) {
        this.kahuaLogoXpath = kahuaLogoXpath;
    }
}
