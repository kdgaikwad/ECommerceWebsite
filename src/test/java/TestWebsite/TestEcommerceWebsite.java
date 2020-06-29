package TestWebsite;

import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import baseClass.ECommerceWebsite;
public class TestEcommerceWebsite {
	//creating object of ECommerceWebsite class as obj_ECommerceWebsite
	ECommerceWebsite obj_ECommerceWebsite = new ECommerceWebsite();

	@BeforeSuite
	public void testOpenBrowser() throws Exception {

		obj_ECommerceWebsite.reportGenerate();
		obj_ECommerceWebsite.openBrowser();

	}

	@AfterSuite
	public void testCloseBrowser() throws InterruptedException {
		obj_ECommerceWebsite.closeBrowser();
	}

	@AfterMethod
	public void Screenshot(ITestResult result) throws IOException {
		obj_ECommerceWebsite.captureScreenshot(result);
	}

	@Test(priority = 0 , description = "TC001--signin--This Testcase Verifies that User is able to Login with  Valid Credentials which are taken from excel sheet")
	public void testSignInPage() throws Exception{
		Thread.sleep(2000);
		obj_ECommerceWebsite.signInPage();

	}

	@Test(priority = 1 , description = "TC002--searchItem--This Testcase Verifies User is able to search the particular item which is taken from Excel sheet. ")
	public void testproductSelection() throws Exception {

		obj_ECommerceWebsite.productSelection();
	}

	@Test(priority = 2 , description = "TC003--addToCart--This Testcase Verifies User is able to add the searched item to Cart" )
	public void testAddToCart() throws Exception{

		obj_ECommerceWebsite.addToCart();
	}

	@Test(priority = 3 , description = "TC004--logout--This Testcase Verifies User is able to logout from website")
	public void testLogOut() throws Exception{

		obj_ECommerceWebsite.logout();
	}

}
