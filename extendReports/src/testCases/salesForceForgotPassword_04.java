package testCases;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.LogStatus;

public class salesForceForgotPassword_04  extends ReusableCode{

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\forgotPW.html";
		startTest(path);

		//Open sales force window
		//Initialize driver
		//Search Web element
		InitializeDriver();
		Launch("https://login.salesforce.com/");
		
		System.out.println("Sales Force page is launched");
		logger.log(LogStatus.INFO, "Salesforce application page is displayed");
		

		//Forgot Password
		WebElement forgotPW=searchElement(By.id("forgot_password_link"),"Forgot Password");
		Thread.sleep(1000);
		forgotPW.click();

		System.out.println("Salesforce forgot password page is displayed");
		logger.log(LogStatus.PASS, "Salesforce forgot password page is displayed");

		//Enter user name
		WebElement uname=searchElement(By.id("un"),"User Name");
		enterText(uname,"User Name","menithya@mywork.com");
		Thread.sleep(1000);

		//Click Continue
		WebElement continu=searchElement(By.id("continue"),"Continue");
		continu.click();

		Thread.sleep(1000);

		System.out.println("Password reset message page is displayed. An email associated with the <username> account is sent.");
		logger.log(LogStatus.PASS, "Password reset message page is displayed. An email associated with the <username> account is sent.");

		
		//Return to Login page
		WebElement rLogin=searchElement(By.xpath("//*[@id='forgotPassForm']/a"),"Return to Login Page");
		Thread.sleep(1000);
		rLogin.click();

		System.out.println("Salesforce login page is displayed");
		logger.log(LogStatus.PASS,"Salesforce login page is displayed");

		//Enter wrong User Name
		WebElement uName1=searchElement(By.id("username"),"User Name");
		enterText(uName1,"User Name","123");
		Thread.sleep(500);

		logger.log(LogStatus.PASS,"Username 123 is entered in username field");

		//Enter wrong Password
		WebElement pw=searchElement(By.id("password"),"Password");
		enterText(pw,"Password","22131");
		Thread.sleep(500);

		logger.log(LogStatus.PASS,"Password is entered in the pwd field");

		//Click Login button
		WebElement login=driver.findElement(By.id("Login"));
		login.click();

		System.out.println("Your login attempt has failed. The username or password may be incorrect. Please contact the administrator at your company for help");
		logger.log(LogStatus.PASS,"Your login attempt has failed. The username or password may be incorrect. Please contact the administrator at your company for help");

		endTest();
		System.out.println("End");
		
		Thread.sleep(1000);
		driver.close();

	}
}
