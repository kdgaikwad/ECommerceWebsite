package baseClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilities.Constant;
import utilities.ExcelUtils;
import utilities.Screenshot;

public class ECommerceWebsite {
	public WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReport;
	public WebDriverWait  wait;
	String Reportfile = System.getProperty("user.dir")+"/test-output/HTMLReport.html";
	
	public void reportGenerate() throws Exception{
		htmlReport = new ExtentHtmlReporter(Reportfile);
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		htmlReport.config().setReportName("Biba Shopping Website Automation Testing ");
		htmlReport.config().setTheme(Theme.STANDARD);
	        htmlReport.config().setDocumentTitle("HtmlReportsTestResults");
	}
	
	public void captureScreenshot(ITestResult result) throws IOException {
		test = extent.createTest(result.getName());
		if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed", ExtentColor.GREEN));
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
				String temp = Screenshot.screenshot(driver, result.getName());
				test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed", ExtentColor.RED));
				test.addScreenCaptureFromPath(temp);
		
		}
		else {
			test.log(Status.SKIP, MarkupHelper.createLabel("Test Method Skiped", ExtentColor.GREY));
		}
		extent.flush();
	}
	
	public void openBrowser() throws Exception {

	Reporter.log("Testcases Exceution Started", true);
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.biba.in/"); 
	driver.manage().window().maximize();
		
	}
	
	
	
	public void signInPage() throws Exception {
		wait = new WebDriverWait(driver,10);
		//identify signin button
		WebElement signin = driver.findElement(By.xpath("//*[@id=\"myaccount\"]/div/div/a[1]"));
		wait.until(ExpectedConditions.visibilityOf(signin));
		signin.click();
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		// fetching username from excel
		String Username = ExcelUtils.getCellData(1, 1);
		//identify username element
		WebElement username = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_UserName\"]")); 
		wait.until(ExpectedConditions.visibilityOf(username));
		if(username.isDisplayed())
		username.click();
		username.sendKeys(Username);
		//fetching password from excel
		String sPassword = ExcelUtils.getCellData(1, 2);
		//identify password element 
		WebElement passwordTxtBox = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_Password\"]"));  
		wait.until(ExpectedConditions.visibilityOf(passwordTxtBox));
		if(passwordTxtBox.isDisplayed())
			passwordTxtBox.sendKeys(sPassword);
		//identify login button 
		WebElement submit = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_LoginImageButton\"]"));
		wait.until(ExpectedConditions.visibilityOf(submit));
		submit.click();
		


}
  
  
	public void productSelection() throws Exception {
		wait = new WebDriverWait(driver,10);
               //enter productname from excelsheet
		String serch = ExcelUtils.getCellData(1, 3);
		//identify searchinput box 
		WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"txtSearch\"]"));
		//identify searchbutton
		WebElement searchButton =driver.findElement(By.xpath("//*[@id=\"btnSearch\"]"));
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		if(searchBox.isDisplayed())
		{
			searchBox.click();
			searchBox.sendKeys(serch);
			searchButton.click();
		}
		 //identify addToBag element
		  WebElement addToBag =driver.findElement(By.xpath("//*[@id=\"3241494\"]/div[2]/div/div[2]/div/h2/a"));
		  wait.until(ExpectedConditions.visibilityOf(addToBag));
		  addToBag.click();
	}
	


  public void addToCart() throws Exception{
		wait = new WebDriverWait(driver,10);
	  //identify size element
	  WebElement size = driver.findElement(By.xpath("//*[@id=\"236992\"]/span"));
	  wait.until(ExpectedConditions.visibilityOf(size));
	  size.click();
	  //identify addToCart element
	  WebElement atcart = driver.findElement(By.xpath("//input[@value='Add to Cart']"));
	  if(atcart.isDisplayed())
	  {
	  atcart.click();
	  }
	  //identify close popup
	  WebElement closePopup= driver.findElement(By.xpath("//*[@id=\"QuickCart\"]/div[1]/a"));
	  wait.until(ExpectedConditions.visibilityOf(closePopup));
	  if(closePopup.isDisplayed())
	  {
		  closePopup.click();
	  }
  }

 

  public void logout() throws Exception{
		wait = new WebDriverWait(driver,10);

	  WebElement myAccount = driver.findElement(By.xpath("//*[@id=\"lblusrn\"]"));
	  wait.until(ExpectedConditions.visibilityOf(myAccount));
	  myAccount.click();
	  WebElement logout  = driver.findElement(By.xpath("//*[@id=\"lnkLogout1\"]"));
	  wait.until(ExpectedConditions.visibilityOf(logout));
	  if(logout.isDisplayed())
	  {
		  logout.click();
		  driver.quit();
		  
	  }
	  
  }

  public void closeBrowser() throws InterruptedException {

	  //sleep java thread for 4 seconds
	  Thread.sleep(4000); 
	  //close all the driver instances
	  driver.quit();

  }
}



	



























