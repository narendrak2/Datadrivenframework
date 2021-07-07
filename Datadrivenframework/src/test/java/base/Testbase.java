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
	//public static wait Webdriverwait;
	
	@BeforeMethod
	public void setup() throws IOException {
		
		if(driver==null) {
		
		FileInputStream fis=new FileInputStream("/Users/narendra/eclipse-workspace/Datadrivenframework/src/test/resources/properties/config.properties");
		config.load(fis);
		fis=new FileInputStream("/Users/narendra/eclipse-workspace/Datadrivenframework/src/test/resources/properties/Objectstorage.properties");
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
		
		Select sc=new Select(driver.findElement(By.id(str)));
		List<WebElement> ls=sc.getOptions();
		for(int i=0;i<ls.size();i++) {
	sc.selectByIndex(i);
		}
		//return str;s
		
	}
		//sc.selectByIndex(2);
		//return str;	
	//}
	@AfterMethod
	public void teardown() {
		
		driver.quit();	
	}	
}

