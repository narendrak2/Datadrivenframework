package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Utilities.Excelreader;

public class Testbase {

	/*
	 * webdriver
	 * properties
	 * logs
	 * extendreports
	 * db
	 * excel
	 * mail
	 * test
	 */
	public static WebDriver driver;
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static Excelreader excel=new Excelreader("/Users/narendra/git/Datadrivenframework/Datadrivenframework/src/test/resources/excel/Attendanceapril3232.xlsx");
	//public static wait Webdriverwait;
	
	@BeforeMethod
	public void setup() throws IOException {
		
		if(driver==null) {
		
		FileInputStream fis=new FileInputStream("/Users/narendra/git/Datadrivenframework/Datadrivenframework/src/test/resources/properties/config.properties");
		config.load(fis);
		fis=new FileInputStream("/Users/narendra/git/Datadrivenframework/Datadrivenframework/src/test/resources/properties/Objectstorage.properties");
		OR.load(fis);
		
		}
		if(config.getProperty("browser").equalsIgnoreCase("safari")) {
			//System.setProperty("webdriver.gecko.driver","firefox.exe");
			driver=new SafariDriver();
		}
			else if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
				log.debug("Chrome starting now");
				System.setProperty("webdriver.chrome.driver","/Users/narendra/Downloads/chromedriver 15");
				driver=new ChromeDriver();
	}
		driver.get(config.getProperty("testsuiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),TimeUnit.SECONDS);
	}
	
	public String findElementAndGetText(String str) {
		
		return driver.findElement(By.xpath(str)).getText();	
	}
	
	public void findelemntAndClickXpath(String str) {
		
		driver.findElement(By.xpath(str)).click();	
	}
public void findelemntAndClick(String str) {
		
		driver.findElement(By.xpath(str)).click();	
	}
	public boolean Checkbuttonenable(By by) {
		
		try {
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e) {
			
			return false;
		}
	}
	public void clickelementandwait(String element){

		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(element)))).click();
	}
    public void findelementAndSendkeys(String str1,String str2) {
		
		 driver.findElement(By.xpath(str1)).sendKeys(str2);
	}
	
	public void dropdownclass(String str) {
		
		Select sc=new Select(driver.findElement(By.xpath(str)));
		sc.selectByIndex(1);

	}
	
	
public void dropdown(String str1) {
		
		Select sc=new Select(driver.findElement(By.xpath(str1)));
		List<WebElement>ls=sc.getOptions();
		for(WebElement we:ls) {
			
            System.out.println(we.getText());
            
		}

	}
	@AfterMethod
	public void teardown() {
		
		driver.quit();	
	}	
}

