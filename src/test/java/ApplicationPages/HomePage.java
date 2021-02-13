package ApplicationPages;

import appLocator.HomePageLocator;
import WebConnector.webconnector;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/*
@Author - Ashish Kr Singh
 */
public class HomePage extends webconnector{

    public HomePageLocator home = new HomePageLocator();

  public HomePage() {
        setUpDriver();
        PageFactory.initElements(driver, home);
      }

    public void goToHomePage() {
        navigateApplication();
        waitForCondition("PageLoad",home.loginCreateAccountBtn,10);
        waitForCondition("Presence",home.loginCreateAccountBtn,10);
    }

    public void clickLoginCreateAccountButton() {
        waitForCondition("Clickable",home.loginCreateAccountBtn,10);
        PerformActionOnElement(home.loginCreateAccountBtn,"ClickUsingJavaScript","");
    }

    public void navigateFlightMenu() {
       try{
           waitForCondition("Visible",home.popupMenu,15);

       }catch (Exception e) {
           JavascriptExecutor executor = (JavascriptExecutor)driver;
           executor.executeScript("return document.getElementsByClassName('autopop__wrap makeFlex column defaultCursor')[0].remove();");
       }
        waitForCondition("Clickable", home.flightMenu, 10);
        PerformActionOnElement(home.flightMenu,"Click","");

    }

    public void selectCityIntoFrom(String city) {
        waitForCondition("Clickable",home.fromCityTxt,10);
        PerformActionOnElement(home.fromCityTxt,"ClickUsingJavaScript","");
        waitForCondition("Visible",home.fromCitySearchTxt,10);
        PerformActionOnElement(home.fromCitySearchTxt,"Click",city);
        PerformActionOnElement(home.fromCitySearchTxt,"Type",city);
        waitForElement(3);
        WebElement we=driver.findElement(By.xpath("//p[contains(text(),'"+city+"')]"));
        waitForCondition("Clickable",we,10);
        PerformActionOnElement(we,"Click","");
    }

    public void selectCityIntoTo(String city) {
        waitForCondition("Clickable",home.toCityTxt,10);
        PerformActionOnElement(home.toCityTxt,"ClickUsingJavaScript","");
        waitForCondition("Visible",home.toCitySearchTxt,10);
        PerformActionOnElement(home.toCitySearchTxt,"Click",city);
        PerformActionOnElement(home.toCitySearchTxt,"Type",city);
        waitForElement(3);
        WebElement we=driver.findElement(By.xpath("//p[contains(text(),'"+city+"')]"));
        waitForCondition("Clickable",we,10);
        PerformActionOnElement(we,"Click","");
    }

    public void selectTravelDate(String date) {
        waitForCondition("Clickable",home.departDate,10);
        PerformActionOnElement(home.departDate,"ClickUsingJavaScript","");
        WebElement we=driver.findElement(By.xpath("//div[@class='DayPicker-Day']/div/p[text()='"+date+"']"));
        waitForCondition("Clickable",we,10);
        PerformActionOnElement(we,"Click","");
    }

    public void selectNumberOfPassengersAndClass(String number,String cl) {
        waitForCondition("Clickable",home.flightTraveller,10);
        PerformActionOnElement(home.flightTraveller,"ClickUsingJavaScript","");
        WebElement we=driver.findElement(By.xpath("//li[@data-cy='adults-"+number+"']"));
        waitForCondition("Clickable",we,10);
        PerformActionOnElement(we,"Click","");
        waitForCondition("Clickable",home.setZeroForChildren,10);
        PerformActionOnElement(home.setZeroForChildren,"Click","");
        waitForCondition("Clickable",home.setZeroForInfant,10);
        PerformActionOnElement(home.setZeroForInfant,"Click","");
        WebElement weForClass=driver.findElement(By.xpath("//li[text()='"+cl+"']"));
        waitForCondition("Clickable",weForClass,10);
        PerformActionOnElement(weForClass,"Click","");

    }

    public void clickApplyButton() {
         waitForCondition("Clickable",home.applyButton,10);
        PerformActionOnElement(home.applyButton,"Click","");
    }

    public void clickSearchButton() {
        waitForCondition("Clickable",home.searchButton,10);
        PerformActionOnElement(home.searchButton,"Click","");
    }

    public void filterFlight(String data) {
        WebElement weFlight = driver.findElement(By.xpath("//p[text()='Popular Filters']/following-sibling::div//span[text()='"+data+"']"));
        waitForCondition("Clickable", weFlight, 10);
        PerformActionOnElement(weFlight, "Click", "");
    }

    public void verifyFilteredFlight(String data) {
        for(int i=0;i<home.flightName.size();i++)
        {
            Assert.assertEquals(home.flightName.get(i).getText(),data,"Filtered Flight is not matching with selected Item");
        }
        System.out.println("Verified correct filtered result for Flight :"+data);
        System.out.println("Showing Filtered Flight count is :: "+home.flightName.size());
    }

}



