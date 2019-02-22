package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class salesForceLogin_2 {

	static ExtentReports report;
	static ExtentTest logger;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\salesForceLogin.html";
		startTest(path);
		
		//Open sales force window
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sysadmin\\selenium\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		logger.log(LogStatus.INFO, "Salesforce login page is displayed");
		
		//Enter user name and password
		WebElement uname=driver.findElement(By.id("username"));
		uname.sendKeys("menithya@mywork.com");
		logger.log(LogStatus.PASS, "Username is displayed in username field");
				
		WebElement pw=driver.findElement(By.id("password"));
		pw.sendKeys("Tpusercool123");
		
		//Click on login button
		WebElement login=driver.findElement(By.id("Login"));
		login.click();
		
		logger.log(LogStatus.PASS, "Welcome to your free trial");
		
		endTest(logger);
		System.out.println("End");
		
	}
	
	public static void startTest(String path)
	{
		report = new ExtentReports(path);
		logger = report.startTest("Login to Sales Force");

	}
	
	public static void endTest(ExtentTest logger)
	{
		report.endTest(logger);
		report.flush();
	}

}
