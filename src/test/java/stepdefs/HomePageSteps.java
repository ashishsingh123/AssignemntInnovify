package stepdefs;

import ApplicationPages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import WebConnector.webconnector;
import io.cucumber.java.en.Given;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;

/*
@Author - Ashish Kr Singh
 */
public class HomePageSteps extends webconnector {
    private HomePage homePage;
    private String scenDesc;

    public HomePageSteps() {
        this.homePage = new HomePage();
    }


    @Given("User navigates to MakemyTrip HomePage")
    public void user_navigates_to_MakemyTrip_HomePage() {
        this.homePage.goToHomePage();
    }

    @When("User click 'Login or Create Account' Button")
    public void user_click_Buy_Now_Button() {
        this.homePage.clickLoginCreateAccountButton();
    }

    @When("Navigate Flight section")
    public void user_navigate_flights() {
        this.homePage.navigateFlightMenu();
    }

    @When("Book flight {string} using testdata file {string}")
    public void book_flight_using_testdata_file(String column, String file) throws IOException, InvalidFormatException {
        String city=getSpecificColumnData(".\\src\\test\\testdata\\"+file, "Testdata", column);

        if(column.equals("From"))
        {
            this.homePage.selectCityIntoFrom(city);
        }else{
            this.homePage.selectCityIntoTo(city);
        }
    }

    @And("Select number of {string} and {string} using testdata file {string}")
    public void selectNumberOfPassengersAndClassUsingTestdataFile(String pass,String cl,String file) throws IOException, InvalidFormatException {
        String passengers=getSpecificColumnData(".\\src\\test\\testdata\\"+file, "Testdata", pass);
        String travel_class=getSpecificColumnData(".\\src\\test\\testdata\\"+file, "Testdata", cl);

        this.homePage.selectNumberOfPassengersAndClass(passengers,travel_class);
        this.homePage.clickApplyButton();
    }

    @Then("Click Search button")
    public void click_Search_button() {
        this.homePage.clickSearchButton();
    }

    @When("Select {string} using testdata file {string}")
    public void user_select_travel_date(String col,String file) throws IOException, InvalidFormatException {
       String date= getSpecificColumnDataForNumeric(".\\src\\test\\testdata\\"+file, "Testdata", col);
        this.homePage.selectTravelDate(date);
    }

    @When("Filter flight:\"([^\"]*)\"")
    public void user_filter_flight(String flight) {
        this.homePage.filterFlight(flight);
    }

    @Then("Verify the filter result is showing \"([^\"]*)\"")
    public void verify_filter_flight(String flight) {
        this.homePage.verifyFilteredFlight(flight);
    }
}