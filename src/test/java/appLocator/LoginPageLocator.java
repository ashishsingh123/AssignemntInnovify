package appLocator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
@Author - Ashish Kr Singh
 */
public class LoginPageLocator {

    @FindBy(id = "username")
    public WebElement emailOrMobileText;

    @FindBy(xpath = "//span[text()='Continue']")
    public WebElement continueButton;

    @FindBy(id = "password")
    public WebElement passwordText;

    @FindBy(xpath = "//span[text()='Login']")
    public WebElement loginButton;

    @FindBy(id = "resetEmailPass")
    public WebElement mobileText;

    @FindBy(id = "otp")
    public WebElement otpTextField;
}
