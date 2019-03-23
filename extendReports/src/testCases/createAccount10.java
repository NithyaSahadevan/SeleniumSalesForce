package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class createAccount10 extends ReusableCode{

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\AddAccount10.html";
		startTest(path);

		//Open sales force window
		//Initialize driver
		//Search Web element
		InitializeDriver();
		Launch("https://login.salesforce.com/");
		
		Thread.sleep(1000);

		System.out.println("Sales Force page is launched");
		logger.log(LogStatus.INFO, "Salesforce application page is displayed");

		String dt_path="C:\\Users\\sysadmin\\Desktop\\SeleniumTraining\\Selenium_TestData.xls";
		String[][] retData=readExcelData(dt_path,"Sheet1");

		//Enter user name
		WebElement uname=searchElement(By.id("username"),"User Name");
		enterText(uname,"User Name",retData[0][0]);

		//Enter Password
		WebElement pw=searchElement(By.id("password"),"Password");
		enterText(pw,"Password",retData[0][1]);
		Thread.sleep(2000);

		//Click on login button
		WebElement login=driver.findElement(By.id("Login"));
		login.click();
		Thread.sleep(2000);

		System.out.println("SalesForce login page is launched and application home page is logged in with correct username.");
		logger.log(LogStatus.INFO, "SalesForce login page is launched and application home page is logged in with correct username.");

		Thread.sleep(2000);
		

		Thread.sleep(2000);

		WebElement dd=driver.findElement(By.xpath("//a[@id='zen-moreTabsAssistiveLink']//b"));
		dd.click();
		
		WebElement acc=driver.findElement(By.linkText("Accounts"));
		acc.click();
		
		Thread.sleep(1000);
		logger.log(LogStatus.PASS, "Accounts page is displayed with correct <username>");
		
		WebElement newAcc=driver.findElement(By.xpath("//input[@title='New']"));
		newAcc.click();
		
		WebElement addAcc=searchElement(By.id("acc2"),"New Account");
		addAcc.sendKeys("abc");
		
		WebElement saveAcc=driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@title='Save']"));
		saveAcc.click();
		
		Thread.sleep(1000);
		logger.log(LogStatus.PASS, "New account page is displayed with account details.");
		
		endTest();
		System.out.println("End");

		Thread.sleep(2000);
		driver.close();
	}

}
