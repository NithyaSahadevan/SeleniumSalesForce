package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.LogStatus;

public class LoginErrMessage_01 extends ReusableCode {



	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\loginError.html";
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
		logger.log(LogStatus.PASS, "Username is displayed in username field");

		//Enter Password
		WebElement pw=searchElement(By.id("password"),"Password");
		enterText(pw,"Password",retData[0][1]);
		Thread.sleep(2000);

		//Clear password field
		if(pw.getText()!=null)
		{
			pw.clear();

		}	

		//Click on login button
		WebElement login=driver.findElement(By.id("Login"));
		login.click();
		
		//Check if the password field is empty
		WebElement err=driver.findElement(By.id("error"));

		if(err.getText()!=null)
		{
			System.out.println("Password field should be empty");
			logger.log(LogStatus.PASS, "Passwordfield should be empty");

			System.out.println("Please enter your password");
			logger.log(LogStatus.FAIL, "Please enter your password");
		}

		endTest();
		System.out.println("End");

		Thread.sleep(1000);
		driver.close();
	}

}

