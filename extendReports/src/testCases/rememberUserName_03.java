package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class rememberUserName_03 extends ReusableCode{

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\rememberUserName.html";
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
		String s1=uname.getText();

		//Enter Password
		WebElement pw=searchElement(By.id("password"),"Password");
		enterText(pw,"Password",retData[0][1]);
		Thread.sleep(2000);

		//Remember username
		WebElement run=searchElement(By.xpath("//*[@id='rememberUn']"),"Remember Me");
		selectCheckBox(run, "Remember Me");

		Thread.sleep(1000);

		//Click on login button
		WebElement login=driver.findElement(By.id("Login"));
		login.click();

		Thread.sleep(1000);

		System.out.println("Salesforce home page is displayed");
		logger.log(LogStatus.PASS, "Salesforce home page is displayed");
		
		Thread.sleep(2000);


		WebElement uMenu=searchElement(By.id("userNavLabel"),"User Menu");
		uMenu.click();

		Thread.sleep(1000);

		WebElement logOut=searchElement(By.linkText("Logout"),"LogOut");
		logOut.click();

		Thread.sleep(1000);
		System.out.println("Login Salesforce home page is displayed with username populated and remember username checked");
		logger.log(LogStatus.PASS, "Login Salesforce home page is displayed with username populated and remember username checked");

		WebElement uName2=searchElement(By.id("username"),"User Name");
		Thread.sleep(1000);
		String s2=uName2.getText();

		if(s1.equals(s2))
		{
			System.out.println("Username is correct");
			logger.log(LogStatus.PASS,"Username is correct");
		}

		endTest();
		System.out.println("End");
		
		//driver.switchTo().defaultContent();
		Thread.sleep(1000);
		driver.close();

	}
}