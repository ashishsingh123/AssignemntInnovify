package WebConnector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import io.cucumber.core.api.Scenario;

/*
@Author - Ashish Kr Singh
 */

public class webconnector {

	public static WebDriver driver;
	public  SessionId session=null;
	public static Properties prop = new Properties();
	
	public webconnector(){
    	try {
    		prop.load( new FileInputStream("./src/test/config/application.properties") );
    	} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public webconnector(WebDriver driver){
        this.driver=driver;
    }
	public WebDriver getDriver() {
		return this.getDriver();
	}
	
	public void setDriver(WebDriver driver){
		this.driver=driver;
	}
	
    public void setUpDriver(){
        String browser = prop.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser) {
            case "chrome":
            	System.setProperty("webdriver.chrome.driver","./src/test/driver/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
            	   //session = ((ChromeDriver)driver).getSessionId();
                driver = new ChromeDriver(chromeOptions);
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            case "firefox":
            	System.setProperty("webdriver.gecko.driver","./src/test/driver/geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                 break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }

    public void navigateApplication()
    {
        String url = prop.getProperty("URL");
        driver.manage().window().maximize();
        driver.get(url);
    }
    public void closeDriver(Scenario scenario){
        if(scenario.isFailed()){
           saveScreenshotsForScenario(scenario);
        }
        driver.close();
    }

    private void saveScreenshotsForScenario(final Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }
    
    public void waitForPageLoad(int timeout){
       ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
    }

    public String getSpecificColumnData(String FilePath, String SheetName, String ColumnName) throws InvalidFormatException, IOException {
    	FileInputStream fis = new FileInputStream(FilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(SheetName);
        XSSFRow row = sheet.getRow(0);
        int col_num = -1;
        for(int i=0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals(ColumnName))
                col_num = i;
        }
        row = sheet.getRow(1);
        XSSFCell cell = row.getCell(col_num);
        String value = cell.getStringCellValue();
        fis.close();

    	return value;
    }

    public String getSpecificColumnDataForNumeric(String FilePath, String SheetName, String ColumnName) throws InvalidFormatException, IOException {
        FileInputStream fis = new FileInputStream(FilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(SheetName);
        XSSFRow row = sheet.getRow(0);
        int col_num = -1;
        for(int i=0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals(ColumnName))
                col_num = i;
        }
        row = sheet.getRow(1);
        XSSFCell cell = row.getCell(col_num);
        int value = (int)cell.getNumericCellValue();
        fis.close();

        return String.valueOf(value);
    }
    
    public void setSpecificColumnData(String FilePath, String SheetName, String ColumnName) throws IOException{
    	FileInputStream fis;
		fis = new FileInputStream(FilePath);
		FileOutputStream fos = null;
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(SheetName);
        XSSFRow row = null;
        XSSFCell cell = null;
        XSSFFont font = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        int col_Num = -1;
        row = sheet.getRow(0);
        for(int i = 0; i < row.getLastCellNum(); i++)
        {
            if(row.getCell(i).getStringCellValue().trim().equals(ColumnName))
            {
                col_Num = i;
            }
        }
        row = sheet.getRow(1);
        if(row == null)
            row = sheet.createRow(1);
        cell = row.getCell(col_Num);
        if(cell == null)
            cell = row.createCell(col_Num);
        font.setFontName("Comic Sans MS");
        font.setFontHeight(14.0);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        cell.setCellValue("PASS");
		fos = new FileOutputStream(FilePath);
        workbook.write(fos);
        fos.close();
    }

    public void PerformActionOnElement(WebElement we, String Action, String Text) {
    	switch (Action) {
		case "Click":
			we.click();
			break;
	    case "ClickUsingJavaScript":
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", we);
            break;
		case "Type":
			we.sendKeys(Text);
			break;
		case "Clear":
			we.clear();
			break;
		case "WaitForElementDisplay":
			waitForCondition("Presence",we,60);
			break;
		case "WaitForElementClickable":
			waitForCondition("Clickable",we,60);
			break;
		case "ElementNotDisplayed":
			waitForCondition("NotPresent",we,60);
			break;
		default:
			throw new IllegalArgumentException("Action \"" + Action + "\" isn't supported.");
		}
    }
    
    public void waitForCondition(String TypeOfWait,  WebElement we, int Time){
    	try {
	    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Time, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);
	    	switch (TypeOfWait)
	    	{
	    	case "PageLoad":
	    		wait.until( ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
	    		break;
	    	case "Clickable":
	    		wait.until(ExpectedConditions.elementToBeClickable(we));
	    		break;
	    	case "Presence":
	    	  	wait.until(ExpectedConditions.visibilityOf(we));
	    		break;
	    	case "Visibility":
	    		wait.until(ExpectedConditions.visibilityOf(we));
	    		break;
	    	case "NotPresent":
	    		wait.until(ExpectedConditions.invisibilityOf(we));
	    		break;
	    	default:
	    		Thread.sleep(Time*1000);
	    	}
    	}
	    	catch(Exception e)
	    	{
	    	    e.printStackTrace();
	    		throw new IllegalArgumentException("wait For Condition \"" + TypeOfWait + "\" isn't supported.");
	    	}
    }

    public void waitForElement(int num)
    {
        try{
            Thread.sleep(num*1000);
        }catch (Exception e){}
    }
}
