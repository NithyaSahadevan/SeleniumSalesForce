package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class salesForceForgotPassword {

	static ExtentReports report;
	static ExtentTest logger;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\forgotPW.html";
		startTest(path);
		
		//Open sales force window
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sysadmin\\selenium\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		logger.log(LogStatus.INFO, "Salesforce login page is displayed");
		
		WebElement forgotPW=driver.findElement(By.id("forgot_password_link"));
		Thread.sleep(1000);
		forgotPW.click();
		
		
		logger.log(LogStatus.PASS, "Salesforce forgot password page is displayed");
		
		WebElement uName=driver.findElement(By.id("un"));
		uName.sendKeys("menithya@mywork.com");
		Thread.sleep(1000);
						
		WebElement continu=driver.findElement(By.id("continue"));
		continu.click();
		
		Thread.sleep(1000);
		
		logger.log(LogStatus.PASS, "Password reset message page is displayed. An email associated with the <username> account is sent.");
		
		WebElement rLogin=driver.findElement(By.xpath("//*[@id='forgotPassForm']/a"));
		Thread.sleep(1000);
		rLogin.click();
		
		logger.log(LogStatus.PASS,"Salesforce login page is displayed");
		
		WebElement uName1=driver.findElement(By.id("username"));
		uName1.sendKeys("123");
		Thread.sleep(500);
		
		logger.log(LogStatus.PASS,"Username 123 is entered in username field");
		
		WebElement pw=driver.findElement(By.id("password"));
		pw.sendKeys("22131");
		Thread.sleep(500);
		
		logger.log(LogStatus.PASS,"Password is entered in the pwd field");
		
		WebElement login=driver.findElement(By.id("Login"));
		login.click();
		
		logger.log(LogStatus.PASS,"Your login attempt has failed. The username or password may be incorrect. Please contact the administrator at your company for help");
		
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
