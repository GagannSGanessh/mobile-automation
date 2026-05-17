package tests;

import base.baseTest;
import pages.loginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.time.Duration;

public class loginTest extends baseTest {

    public void takeScreenshot(String name) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }

    @Test(description = "Valid login should succeed")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login")
    @Description("User should see success popup with valid credentials")
    public void testValidLogin() {
        loginPage page = new loginPage(driver);
        page.login("alice@example.com", "alice12345");
        takeScreenshot("After valid login click");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean successShown = wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.id("android:id/button1")
        )).isDisplayed();
        takeScreenshot("Success popup");
        Assert.assertTrue(successShown, "Success popup should be displayed");
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

    @Test(description = "Invalid email should show validation error")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login")
    @Description("Invalid email format should show error message")
    public void testInvalidLogin() {
        loginPage page = new loginPage(driver);
        page.login("notanemail", "somepassword123");
        takeScreenshot("After invalid login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean errorShown = wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.xpath("//android.widget.TextView[@text='Please enter a valid email address']")
        )).isDisplayed();
        takeScreenshot("Error message shown");
        Assert.assertTrue(errorShown, "Email validation error should be displayed");
    }

    @Test(description = "Short password should show validation error")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login")
    @Description("Password less than 8 characters should show error")
    public void testShortPassword() {
        loginPage page = new loginPage(driver);
        page.login("alice@example.com", "abc");
        takeScreenshot("After short password");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean errorShown = wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.xpath("//android.widget.TextView[@text='Please enter at least 8 characters']")
        )).isDisplayed();
        takeScreenshot("Password error shown");
        Assert.assertTrue(errorShown, "Password length error should be displayed");
    }
}