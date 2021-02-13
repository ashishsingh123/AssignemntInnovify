package ApplicationPages;

import WebConnector.webconnector;
import appLocator.LoginPageLocator;
import org.openqa.selenium.support.PageFactory;

/*
@Author - Ashish Kr Singh
 */
public class LoginPage extends webconnector{

    webconnector wc=new webconnector();
    public LoginPageLocator login = new LoginPageLocator();


    public LoginPage() {
        PageFactory.initElements(driver, login);
    }

    public void enterEmail(String text)  {
        wc.waitForCondition("Visible",login.emailOrMobileText,20);
        wc.PerformActionOnElement(login.emailOrMobileText,"Type",text);
    }

    public void clickContinueButton()  {
        wc.waitForCondition("Clickable",login.continueButton,20);
        wc.PerformActionOnElement(login.continueButton,"Click","");
    }

    public void enterPassword(String text)  {
        wc.waitForCondition("Visible",login.passwordText,20);
        wc.PerformActionOnElement(login.passwordText,"Type",text);
    }

    public void clickLoginButton()  {
        wc.waitForCondition("Clickable",login.loginButton,20);
        wc.PerformActionOnElement(login.loginButton,"Click","");
    }

    public void enterMobileNumber(String mobile)  {
        wc.waitForCondition("Visible",login.mobileText,20);
        wc.PerformActionOnElement(login.mobileText,"Type",mobile);
    }

    public void verifyOtpField()  {
        wc.waitForCondition("Visibility",login.otpTextField,20);
        System.out.println(" Verified OTP field is displayed ");
    }

}
