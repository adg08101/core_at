package general;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WaitingObject {

    private final WebDriver driver;

    public WaitingObject(WebDriver driver) {
        this.driver = driver;
        this.waitForLoading(20);
    }

    /**
     *
     * @param time to wait in seconds
     */
    public void waitForLoading(long time) {
        driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
    }

    /**
     *
     * @param time to wait on thread
     */
    public void thread(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
