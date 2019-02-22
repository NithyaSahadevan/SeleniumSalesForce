package extendReports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class seleniumExReports {

	static ExtentReports report;
	static ExtentTest logger;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Initialize extend Report
		String path="D:\\SeleniumTraining\\extendReports\\Report1.html";
		report=new ExtentReports(path);
		
		//Initialize logger
		logger=report.startTest("Login to SFDC");
		
		logger.log(LogStatus.INFO, "Firefox browser launched");
		logger.log(LogStatus.PASS, "Username Enter successfully");
		logger.log(LogStatus.FAIL, "Password field does not exist");
		logger.log(LogStatus.PASS, "Login button clicked");
		
		report.endTest(logger);
		
		//Initialize logger
		logger=report.startTest("Rememeber password on SFDC");
		
		logger.log(LogStatus.INFO, "Firefox browser launched");
		logger.log(LogStatus.PASS, "Username Enter successfully");
		logger.log(LogStatus.PASS, "Password Entered");
		logger.log(LogStatus.PASS, "Login button clicked");
		
		report.endTest(logger);
		
		report.flush();
		
		System.out.println("End");
	}
	
	
}
