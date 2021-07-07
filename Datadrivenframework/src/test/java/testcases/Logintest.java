package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.Testbase;

 class Logintest extends Testbase{
	
	
	@Test(priority=1)
	public void loginmanager() throws InterruptedException {
		log.debug("On Login page");
		clickelementandwait(OR.getProperty("customerloginbutton"));
		
		String str=findElementAndGetText(OR.getProperty("checkname"));
		Assert.assertEquals(str, "Your Name :");
	    log.debug("Login got success");
	    
	    dropdownclass(OR.getProperty("namedropdown"));
	   
	    findelemntAndClickXpath(OR.getProperty("Loginbutton"));
	    Thread.sleep(2000);
	    
	    String str1=findElementAndGetText(OR.getProperty("logout"));
	   
	    Assert.assertEquals(str1, "Logout");
	    
	}
@Test(priority=2)
public void deposit() throws InterruptedException {
	
	loginmanager();
	log.debug("Stating of next page deposit");
	Thread.sleep(2000);
	findelemntAndClickXpath(OR.getProperty("depositbtn"));
	String str=findElementAndGetText(OR.getProperty("textondeposit"));
	Assert.assertEquals(str, "Amount to be Deposited :");
	Thread.sleep(2000);
	findelementAndSendkeys(OR.getProperty("amountsendkeys"),OR.getProperty("amount"));
	Thread.sleep(2000);
	findelemntAndClickXpath(OR.getProperty("depositbutton"));
	Thread.sleep(2000);
	String str1=findElementAndGetText(OR.getProperty("successdepositmsg"));
	   
    Assert.assertEquals(str1, "Deposit Successful");
	
}

@Test(priority=3)

public void transcation() throws InterruptedException {
	
	
	log.debug("On Login page for transaction");
	loginmanager();
	findelemntAndClickXpath(OR.getProperty("Transactionbtn"));
	log.debug("After click transcation button and wait ");
	Thread.sleep(3000);
	String str1=findElementAndGetText(OR.getProperty("resetbtontranspage"));
	   
    Assert.assertEquals(str1, "Reset");
}
	
}
