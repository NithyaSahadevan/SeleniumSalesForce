package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginErrMessage {

	static ExtentReports report;
	static ExtentTest logger;
	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\loginError.html";
		startTest(path);
		
		//Open sales force window
		//Initialize driver
		//Search Web element
		InitializeDriver();
		Launch("https://login.salesforce.com/");

		driver.manage().window().maximize();
		
		logger.log(LogStatus.INFO, "Salesforce application page is displayed");
		
		//Enter user name
		WebElement uname=driver.findElement(By.id("username"));
		enterText(uname,"menithya@mywork.com");
		logger.log(LogStatus.PASS, "Username is displayed in username field");
		
		WebElement pw=driver.findElement(By.id("password"));
		enterText(pw,"Tpusercool123");
		Thread.sleep(2000);
		
		//Clear password field
		if(pw.getText()!=null)
		{
			pw.clear();
			
		}	
		
		//Click on login button
		WebElement login=driver.findElement(By.id("Login"));
		login.click();
		WebElement err=driver.findElement(By.id("error"));
		
		if(err.getText()!=null)
		{
			logger.log(LogStatus.PASS, "Passwordfield should be empty");
			logger.log(LogStatus.FAIL, "Please enter your password");
		}
		
		
		
		endTest(logger);
		System.out.println("End");
		
		driver.close();
	}
	
	public static void enterText(WebElement obj,String textValue)
	{
		if(obj==null)
		{
			return;
		}
		if(obj.isDisplayed())
		{
			obj.sendKeys(textValue);
		}
		else
		{
			System.out.println("Fail: "+obj+" could not be found");
		}
	}
	
	public static void InitializeDriver() 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sysadmin\\selenium\\chromedriver.exe");
		driver =new ChromeDriver();
	}
	
	public static void Launch(String url)
	{
		driver.get(url);
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

