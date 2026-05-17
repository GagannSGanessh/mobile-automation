package base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class baseTest {

    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel_3a_API_34");
        options.setPlatformVersion("14.0");
        options.setApp(System.getProperty("user.dir") + "/apps/wdio-demo.apk");
        options.setAutoGrantPermissions(true);
        options.setNewCommandTimeout(Duration.ofSeconds(120));

        driver = new AndroidDriver(
            new URL("http://127.0.0.1:4723/wd/hub"), options
        );
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.accessibilityId("Login")
        )).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.accessibilityId("input-email")
        ));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}