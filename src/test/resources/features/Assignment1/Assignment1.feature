# @Author-Ashish Kr Singh
Feature: Check Login functionality with EmailID

  @assignment1
  Scenario Outline: Login functionality
    Given User navigates to MakemyTrip HomePage
    When User click 'Login or Create Account' Button
    When User enter "<Email>" into 'Email or Mobile Number' field
    And  User click Continue Button
    When User enter "<Password>" into 'Password' field
    And User click Login Button
    Then Verify OTP field is displayed

    Examples:
    |Email|Password|
    |ashish015.jss@gmail.com|360@Logica1|
