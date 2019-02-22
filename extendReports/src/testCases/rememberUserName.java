package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class rememberUserName {

	static ExtentReports report;
	static ExtentTest logger;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\rememberUserName.html";
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
		String s1=uname.getText();
				
		WebElement pw=driver.findElement(By.id("password"));
		pw.sendKeys("Tpusercool123");
		
		//Remember username
		WebElement run=driver.findElement(By.xpath("//*[@id='rememberUn']"));
		selectCheckBox(run, "Remember Me");
		
		Thread.sleep(1000);
		
		//Click on login button
		WebElement login=driver.findElement(By.id("Login"));
		login.click();
		
		logger.log(LogStatus.PASS, "Salesforce home page is displayed");
		
		WebElement popUp=driver.findElement(By.id("tryLexDialogX"));
		popUp.click();
		
		WebElement un=driver.findElement(By.id("userNavLabel"));
		un.click();
		
		Thread.sleep(2000);
		
		WebElement logOut=driver.findElement(By.linkText("Logout"));
		Thread.sleep(2000);
		logOut.click();
		
		logger.log(LogStatus.PASS, "Login Salesforce home page is displayed with username populated and remember username checked");
		
		WebElement uname2=driver.findElement(By.id("username"));
		String s2=uname2.getText();
		if(s1.equals(s2))
		{
			logger.log(LogStatus.PASS,"Username is correct");
		}
		
		endTest(logger);
		System.out.println("End");
	}
	
	
	
	public static void selectCheckBox(WebElement obj, String objName)
	{
		if (obj.isDisplayed()) {
			if(!obj.isSelected())
			{
				obj.click();
			}
		}
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
