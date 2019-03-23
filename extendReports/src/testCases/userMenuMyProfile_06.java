package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.relevantcodes.extentreports.LogStatus;

public class userMenuMyProfile_06 extends ReusableCode{

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\UserMenuMyProfile_06.html";
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

		/*PostLink();

		Thread.sleep(2000);

		Click on User Menu*/
		/*WebElement un=driver.findElement(By.id("userNavLabel"));
		un.click();

		Thread.sleep(1000);

		System.out.println("Drop down with \"My profile\", \"My Settings\", \"Developer Console\",\"Logout\" is displayed");
		logger.log(LogStatus.PASS,"Drop down with \"My profile\", \"My Settings\", \"Developer Console\",\"Logout\" is displayed");*/

		//changeLastName();
		
		
		//fileUpload();
		
		
		/*WebElement  mSet=driver.findElement(By.linkText("My Settings"));
		mSet.click();*/
		photoUpload();
		
		endTest();
		System.out.println("End");

		Thread.sleep(1000);
		driver.close();

	}



}
