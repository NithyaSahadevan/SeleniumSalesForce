package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ReusableCode {

	static ExtentReports report;
	protected static ExtentTest logger;
	protected static WebDriver driver;
	static WebDriverWait wait;
	public static void InitializeDriver() 
	{
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\sysadmin\\selenium\\chromedriver.exe");
		//driver =new ChromeDriver();

		System.setProperty("webdriver.gecko.driver", "C:\\Users\\sysadmin\\selenium\\geckodriver-v0.21.0-win64\\geckodriver.exe");
		driver=new FirefoxDriver();
	}

	public static void Launch(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();

	}
	public static void closeDialog()
	{
		//Close the dialog box
		WebElement popUp=searchElement(By.cssSelector("#tryLexDialogX"),"Dialog Box");
		//WebElement popUp=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#tryLexDialogX")));
		popUp.click();
	}
	public static void enterText(WebElement obj,String objName,String textValue)
	{
		if(obj==null)
		{
			return;
		}
		if(obj.isDisplayed())
		{
			obj.sendKeys(textValue);
			System.out.println("Pass: "+textValue+" entered in "+objName);
		}
		else
		{
			System.out.println("Fail: "+objName+" could not be found");
		}
	}

	public static void selectCheckBox(WebElement obj, String objName)
	{
		if(obj == null)
			return;
		if (obj.isDisplayed()) {
			if(!obj.isSelected())
			{
				obj.click();
			}
			System.out.println("Pass: checkbox " + objName + " is selected");
		} else {
			System.out.println("Fail: " + objName + " could not be found");
		}
	}

	public static WebElement searchElement(By location,String objName)
	{
		WebElement obj = null;
		try {
			obj = driver.findElement(location);
			System.out.println("Pass: " + objName + " found on the page.");
		} catch (NoSuchElementException errMessage) {
			System.out.println("Fail: " + objName + " cound not be found on the page.");
		}
		return obj;
	}
	public static String[][] readExcelData(String dataTablePath,String sheetName) throws IOException
	{

		//Step 1: Get the XL path
		File xlFile=new File(dataTablePath);

		//Step 2: Access the XL file
		FileInputStream xlDoc=new FileInputStream(xlFile);

		//Step 3: Access the work book
		HSSFWorkbook wb=new HSSFWorkbook(xlDoc);

		//Step 4: Access the sheet
		HSSFSheet sheet=wb.getSheet(sheetName);

		int iRowCount=sheet.getLastRowNum()+1;
		int iColCount=sheet.getRow(0).getLastCellNum();

		String[][] xlData=new String[iRowCount][iColCount];
		for(int i=0;i<iRowCount;i++)
		{
			for(int j=0;j<iColCount;j++)
			{

				xlData[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				//System.out.print(val+"  ");
			}
		}
		return xlData;
	}

	//Function to Post a Text
	public static void PostLink() throws InterruptedException
	{
		//*[@id="publisherAttachTextPost"]
		//WebElement post=searchElement(By.xpath("//*[@id='publisherAttachTextPost']"),"Post Link");
		WebElement post=searchElement(By.id("publisherAttachTextPost"),"Post Link");
		post.click();

		wait=new WebDriverWait(driver,40);

		WebElement frame=searchElement(By.tagName("iframe"),"Frame");
		driver.switchTo().frame(frame);
		System.out.println("Switched to frame"+ frame);
		WebElement messagebox=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body")));
		messagebox.click();
		System.out.println("Msg box clicked");
		Thread.sleep(2000);

		WebElement messagebox1=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body")));
		System.out.println("Sending msg to post");
		messagebox1.sendKeys("Automated Post sending from eclipse");
		Thread.sleep(2000);

		String s=messagebox1.getText();
		driver.switchTo().defaultContent();
		System.out.println("back to default frame");
		WebElement shareButtton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='publishersharebutton']")));
		shareButtton.click();
		System.out.println("Share clicked");

		System.out.println(s+": Text entered displayed on the page");
		logger.log(LogStatus.PASS,s+" :Text entered displayed on the page.");
	}


	//Function to change the Last Name
	public static void changeLastName() throws InterruptedException
	{
		//Select MyProfile from User Menu
		WebElement myProfile=searchElement(By.xpath(".//*[@id='userNav-menuItems']/a[1]"),"My Profile");
		myProfile.click();

		Thread.sleep(1000);
		System.out.println("User profile page is displayed");
		logger.log(LogStatus.PASS,"User profile page is displayed");

		WebElement editButton=searchElement(By.cssSelector("#moderatorMutton"),"Edit Button");
		editButton.click();

		WebElement editProfile=searchElement(By.xpath("//a[@title='Edit Profile']"),"Edit Profile");
		editProfile.click();

		Thread.sleep(500);
		System.out.println("Edit profile pop up window is displayed with contact and About tabs to edit.");
		logger.log(LogStatus.PASS,"Edit profile pop up window is displayed with contact and About tabs to edit.");

		driver.switchTo().frame("aboutMeContentId");

		WebElement about=searchElement(By.xpath(".//*[@id='aboutTab']/a"),"About");
		about.click();
		WebElement lastName=searchElement(By.xpath(".//*[@id='lastName']"),"Last Name");
		lastName.clear();

		WebElement lName=driver.findElement(By.id("lastName"));
		lName.clear();
		lName.sendKeys("Ganesh");

		WebElement saveAll=searchElement(By.xpath("//input[@value='Save All']"),"Save All");
		saveAll.click();

		Thread.sleep(500);
		System.out.println("User Profile Page with changed <lastname> is displayed.");
		logger.log(LogStatus.PASS,"User Profile Page with changed <lastname> is displayed.");
	}
	public static void fileUpload() throws InterruptedException
	{
		WebElement file=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='publisherAttachContentPost']/span[1]")));
		file.click();
		System.out.println("clicked on file");
		WebElement upload=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='chatterUploadFileAction']")));
		upload.click();
		System.out.println("clicked on upload");
		WebElement browse=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='chatterFile']")));
		browse.sendKeys("C:\\Users\\sysadmin\\Desktop\\TestData.xls");
		Thread.sleep(1000);
		WebElement share=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='publishersharebutton']")));
		share.click();
		System.out.println("File uploaded.");
		logger.log(LogStatus.PASS,"File Uploaded.");
		Thread.sleep(5000);
	}

	public static void photoUpload() throws InterruptedException
	{

		WebElement ph=driver.findElement(By.id("userThumbnailPhoto"));
		ph.click();
		//Add photo
		Actions mousehoover=new Actions(driver);
		mousehoover.moveToElement(driver.findElement(By.xpath("//*[text()='Moderator']"))).perform();
		
		WebElement addPhoto=driver.findElement(By.xpath(".//*[@id='uploadLink']"));
		addPhoto.click();
		Thread.sleep(5000);
		//*[@id="j_id0:uploadFileForm:uploadInputFile"]
		
		WebElement photoframe=driver.findElement(By.id("uploadPhotoContentId"));
		   driver.switchTo().frame(photoframe);
		   WebElement browsePhoto=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadInputFile']")));
		browsePhoto.sendKeys("C:\\Users\\sysadmin\\Desktop\\SeleniumTraining\\images.jpg");
		Thread.sleep(3000);
		WebElement savePhoto=driver.findElement(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadBtn']"));
		savePhoto.click();
		Actions action=new Actions(driver); 
		action.dragAndDropBy(driver.findElement(By.xpath(".//*[@id='j_id0:outer']/div[1]/div/div/div/div[6]")), 100, 20);
		driver.findElement(By.xpath(".//*[@id='j_id0:j_id7:save']")).click(); 
		driver.switchTo().defaultContent();
		System.out.println("Test case 6 Completed");
	}

	public static void loginHistory() throws InterruptedException
	{		
		WebElement dd1 = driver.findElement(By.xpath(".//*[@id='userNav-arrow']"));
		dd1.click();
		System.out.println("Dropdown is clicked");

		WebElement mySettingLink = driver.findElement(By.xpath("//a[@title='My Settings']"));
		mySettingLink.click();
		System.out.println("My Settings link is clicked");

		WebElement personalLink = driver
				.findElement(By.xpath("//div[@id='PersonalInfo']//a[@class='header setupFolder']"));
		personalLink.click();
		System.out.println("Personal Link link is clicked");

		WebElement loginHistoryLink = driver.findElement(By.xpath("//a[@id='LoginHistory_font']"));
		loginHistoryLink.click();
		System.out.println("Login History Link is clicked");

		WebElement downloadLink = driver
				.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
		downloadLink.click();
		System.out.println("DownLoad Link is clicked");

		Thread.sleep(2000);
		Alert myAlert = driver.switchTo().alert();
		Thread.sleep(2000);
		myAlert.accept();

	}

	public static void displayLayoutLink() throws InterruptedException
	{


		WebElement displayLayout = driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']"));
		displayLayout.click();
		System.out.println("Display Layout is clicked");

		Thread.sleep(1000);
		WebElement customizeMyTab = driver.findElement(By.xpath("//a[@id='CustomizeTabs_font']"));
		customizeMyTab.click();
		System.out.println("Customize My Tab is clicked");

		Thread.sleep(1000);
		Select customApp = new Select(driver.findElement(By.xpath("//select[@id='p4']")));
		customApp.selectByVisibleText("Salesforce Chatter");
		System.out.println("Custom App is clicked");
		// Add Reports to Selected Tab
		Boolean found = false;
		java.util.List<WebElement> selectedTabsOptions = driver.findElements(By.xpath("//select[@id='duel_select_1']"));

		Thread.sleep(1000);
		for (WebElement e : selectedTabsOptions) {

			if (e.getText().contains("Reports")) {
				found = true;
			}
		}

		if (found) {
			System.out.println("Reports is already added");
		} else {
			System.out.println("Reports not added");
			Select availableTabs = new Select(driver.findElement(By.xpath("//select[@id='duel_select_0']")));
			availableTabs.selectByVisibleText("Reports");
			System.out.println("Custom App is clicked");

			Thread.sleep(1000);
			WebElement addBtn = driver.findElement(By.xpath("//img[@title='Add']"));
			addBtn.click();
			System.out.println("Add button is clicked");

			driver.findElement(By.xpath("//input[@title='Save']")).click();
			System.out.println("Validation --> Reports is Added");
		}

		WebElement saveBtn = driver.findElement(By.xpath("//input[@title='Save']"));
		saveBtn.click();
		System.out.println("Save is clicked");

	}
	public static void startTest(String path)
	{
		report = new ExtentReports(path);
		logger = report.startTest("Login to Sales Force");

	}

	public static void endTest()
	{
		report.endTest(logger);
		report.flush();
		
	}
}
