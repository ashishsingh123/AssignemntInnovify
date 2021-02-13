# @Author-Ashish Kr Singh
Feature: Book a flight

  @assignment2
  Scenario Outline: Book a flight with the below test data file
    Given User navigates to MakemyTrip HomePage
    And   Navigate Flight section
    When  Book flight 'From' using testdata file 'Data.xlsx'
    And   Book flight 'To' using testdata file 'Data.xlsx'
    And   Select 'TravelDate' using testdata file 'Data.xlsx'
    And   Select number of 'Passengers' and 'Class' using testdata file 'Data.xlsx'
    Then  Click Search button
    When  Filter flight:"<Flight>"
    Then  Verify the filter result is showing "<Flight>"
    Examples:
    |Flight|
    |Air India|
