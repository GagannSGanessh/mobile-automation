package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

    AndroidDriver driver;

    @AndroidFindBy(accessibility = "input-email")
    private WebElement usernameField;

    @AndroidFindBy(accessibility = "input-password")
    private WebElement passwordField;

    @AndroidFindBy(accessibility = "button-LOGIN")
    private WebElement loginButton;

    public loginPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void login(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }
}