package testCases;

import java.awt.AWTException;

import java.io.IOException;
import java.util.Random;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class TestCase07 extends ReusableCode{

	public static void calendarReminder() throws InterruptedException
	{

		driver.findElement(By.xpath("//span[@id='CalendarAndReminders_font']")).click();
		System.out.println("Calendar And Reminder Link is Clicked");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@id='Reminders_font']")).click();
		System.out.println("Activity Reminders Link is Clicked");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='testbtn']")).click();
		System.out.println("Open Test Reminder is clicked");

		Thread.sleep(1000);

		String subWindowHandler = driver.getWindowHandle();

		if (subWindowHandler.isEmpty()) {
			System.out.println("Pop Reminder window is not dispalyed");
		} else {
			driver.switchTo().window(subWindowHandler); // switch to popup window
			System.out.println(
					"Validation of pop up window--> Pop Reminder window " + driver.getTitle() + " is displayed");

		}
		
		Thread.sleep(2000);

		/*if(driver.switchTo().alert() != null)
		{
		    Alert alert = driver.switchTo().alert();
		   // String alertText = alert.getText();
		    alert.dismiss(); 
		    //alert.accept();

		}*/
		String winHandleBefore = driver.getWindowHandle(); 
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);}
		driver.close();
		
		driver.switchTo().window(winHandleBefore);
	}
	
	public static void checkEmail() throws InterruptedException
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		String exp_nameString = "Johny Jane" + randomInt;
		String exp_emailAddressString = "jj" + randomInt + "@gmail.com";

		WebElement emailLink = driver.findElement(By.xpath("//span[@id='EmailSetup_font']"));
		emailLink.click();
		System.out.println("Email is clicked ");

		Thread.sleep(1000);
		WebElement myEmailSettinngs = driver.findElement(By.xpath("//a[@id='EmailSettings_font']"));
		myEmailSettinngs.click();
		System.out.println("My Email Settings is clicked");

		Thread.sleep(1000);
		WebElement senderName = driver.findElement(By.xpath("//input[@id='sender_name']"));
		senderName.clear();
		senderName.sendKeys(exp_nameString);
		;
		System.out.println("Name is entered");

		Thread.sleep(1000);
		WebElement emailAddress = driver.findElement(By.xpath("//input[@id='sender_email']"));
		emailAddress.clear();
		Thread.sleep(500);
		emailAddress.sendKeys(exp_emailAddressString);
		System.out.println("Email Address is Entered");

		Thread.sleep(1000);
		WebElement bccRadio = driver.findElement(By.xpath("//input[@id='auto_bcc1']"));
		bccRadio.click();
		System.out.println("Bcc Radio btn is selected");


		Thread.sleep(1000);
		WebElement saveBtn = driver.findElement(By.xpath("//input[@title='Save']"));
		saveBtn.click();
		System.out.println("Save is clicked");

		// Switching to Alert
		Alert alert = driver.switchTo().alert();

		alert.accept();

		Thread.sleep(2000);

		if (driver.findElement(By.xpath("//img[@title='Confirmation']")).isDisplayed()
				&& driver.findElement(By.xpath("//h1[text()='My Email Settings']")).isDisplayed()) {
			System.out.println("Validation-->Changes are successfully saved and My Email Setting Page is displayed ");
		} else {
			System.out.println("Changes are not saved");
		}

		String actual_nameString = driver.findElement(By.xpath("//input[@id='sender_name']")).getAttribute("value")
				.toString();
		String actual_emailAddress = driver.findElement(By.xpath("//input[@id='sender_email']")).getAttribute("value")
				.toString();

		// Validate Expected and actual Name
		if (actual_nameString.equals(exp_nameString)) {
			System.out.println("Validation of Name-->" + actual_nameString + " is equals to " + exp_nameString);
		} else {
			System.out.println(actual_nameString + " is not equals to " + exp_nameString);
		}

		// Validate Email address (Confirmation email is sent to updated email address.
		// Manually confirm it and then updated email Id reflects.)
		if (actual_emailAddress.equals(exp_emailAddressString)) {
			System.out.println(
					"Validation of Email Address-->" + actual_emailAddress + " is equals to " + exp_emailAddressString);
		} else {
			System.out.println(actual_emailAddress + " is not equals to " + exp_emailAddressString);
		}

		// Check if Radio button is selected
		if (driver.findElement(By.xpath("//input[@id='auto_bcc1']")).isSelected()) {

			System.out.println("Validation of Radio button --> Bcc radio button is Selected ");

		} else {
			System.out.println("Bcc radio button is NOT Selected ");

		}

	}
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\TestCase07.html";
		startTest(path);

		//Open sales force window
		//Initialize driver
		//Search Web element
		InitializeDriver();
		Launch("https://login.salesforce.com/");

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

		WebElement dd1 = driver.findElement(By.xpath(".//*[@id='userNav-arrow']"));
		dd1.click();
		System.out.println("Dropdown is clicked");


		WebElement mySettingLink = driver.findElement(By.xpath("//a[@title='My Settings']"));
		mySettingLink.click();
		System.out.println("My Settings link is clicked");

		//loginHistory();

		//displayLayoutLink();

		//checkEmail();
		
		calendarReminder();

		
		endTest();
		System.out.println("End");

		Thread.sleep(2000);
		driver.close();
	}

}
