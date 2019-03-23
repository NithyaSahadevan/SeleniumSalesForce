package testCases;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class developerConsole08  extends ReusableCode{

	public static void main(String[] args) throws InterruptedException, IOException{
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		String path;
		path="D:\\SeleniumTraining\\TestCasesExtentReport\\TestCase08.html";
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

		Thread.sleep(3000);


		WebElement dd1 = driver.findElement(By.xpath(".//*[@id='userNav-arrow']"));
		dd1.click();
		System.out.println("Dropdown is clicked");

		WebElement dd2 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[3]/div/div[3]/div/div/div[2]/div[3]/a[3]"));
		dd2.click();   

		//store instance of main window first using below code
		String winHandleBefore = driver.getWindowHandle(); 

		//Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		
		Thread.sleep(2000);


		// Perform the actions on new window
		driver.close(); //this will close new opened window

		//switch back to main window using this code
		driver.switchTo().window(winHandleBefore);

		Thread.sleep(2000);

		WebElement uMenu = driver.findElement(By.xpath(".//*[@id='userNav-arrow']"));
		uMenu.click();
		System.out.println("Dropdown is clicked");
		
		WebElement logOut = driver.findElement(By.linkText("Logout"));
		logOut.click();		

		endTest();
		System.out.println("End");

		Thread.sleep(2000);
		driver.close();

	}

}
