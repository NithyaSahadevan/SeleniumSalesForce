package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class salesForceLogin_02  extends ReusableCode{

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\salesForceLogin.html";
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

		logger.log(LogStatus.PASS, "Welcome to your free trial");

		endTest();
		System.out.println("End");
		
		Thread.sleep(2000);
		driver.close();

	}

}
