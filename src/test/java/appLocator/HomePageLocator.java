package appLocator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

/*
@Author - Ashish Kr Singh
 */
public class HomePageLocator {

    @FindBy(css = "div.makeFlex.column.flexOne.whiteText.latoBold")
    public WebElement loginCreateAccountBtn;

    @FindBy(xpath = "//label[@for='fromCity']")
    public WebElement fromCityTxt;

    @FindBy(css = "li.menu_Flights")
    public WebElement flightMenu;

    @FindBy(xpath = "//input[@placeholder='From']")
    public WebElement fromCitySearchTxt;

    @FindBy(xpath = "//label[@for='toCity']")
    public WebElement toCityTxt;

    @FindBy(xpath = "//input[@placeholder='To']")
    public WebElement toCitySearchTxt;

    @FindBy(xpath = "//label[@for='travellers']")
    public WebElement flightTraveller;

    @FindBy(xpath = "//li[@data-cy='children-0']")
    public WebElement setZeroForChildren;

    @FindBy(xpath = "//li[@data-cy='infants-0']")
    public WebElement setZeroForInfant;

    @FindBy(xpath = "//button[text()='APPLY']")
    public WebElement applyButton;

    @FindBy(xpath = "//p[@data-cy='submit']")
    public WebElement searchButton;

    @FindBy(xpath = "//p[@data-cy='departureDate']")
    public WebElement departDate;

    @FindBy(css = "span.boldFont.blackText.airlineName")
    public List<WebElement> flightName;

    @FindBy(css = "div.autopop__wrap.makeFlex.column.defaultCursor")
    public WebElement popupMenu;

}
