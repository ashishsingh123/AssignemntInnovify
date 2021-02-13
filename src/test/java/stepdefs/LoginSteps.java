package stepdefs;

import ApplicationPages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/*
@Author - Ashish Kr Singh
 */
public class LoginSteps {
    private LoginPage login;

    public LoginSteps() {
        this.login = new LoginPage();
    }

    @When("User enter \"([^\"]*)\" into 'Email or Mobile Number' field")
    public void user_enter_email(String email) {
        this.login.enterEmail(email);
    }

    @When("^User click Continue Button$")
    public void user_click_continue() {
        this.login.clickContinueButton();
    }

    @When("User enter \"([^\"]*)\" into 'Password' field")
    public void user_enter_password(String password) {
        this.login.enterPassword(password);
    }

    @Then("^User click Login Button$")
    public void user_click_login_button() {
        this.login.clickLoginButton();
    }

    @When("^User enter \"([^\"]*)\" into 'Mobile' field$")
    public void user_enter_mobile(String mobile) {
        this.login.enterMobileNumber(mobile);
    }

    @When("^Verify OTP field is displayed$")
    public void user_click_Send_otp() {
        this.login.verifyOtpField();
    }

}
