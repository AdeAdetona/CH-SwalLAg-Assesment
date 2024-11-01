package swaglabsTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SwagLabsNegativeTestAutomationClass {
	
	private static WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;

	private void instantiatePages(WebDriver driver){
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);
	}
	
	@BeforeMethod
	public void beforeTest() {

		WebDriverManager.chromedriver().setup();
	    driver  = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    driver.get("https://www.saucedemo.com/v1/index.html");
		instantiatePages(driver);
		loginPage.enterUsername(SwagLabsConstants.PROBLEM_USERNAME);
		loginPage.enterPassword(SwagLabsConstants.PASSWORD);
		loginPage.clickLoginButton();
		homePage.waitForProductLabelToBeVisible();
	}
	
	@Test
	public void validatePriceOfArticleAtCheckoutPage() {
		String articleName = homePage.getArticleName();
		homePage.clickAddArticle();
		String cartSize = homePage.cartSize();
		assertEquals(cartSize, "1","Article does not add to the cart page");
		homePage.clickShoppingCartContainer();
		cartPage.waitForSubHeaderVisible();
		String cartArticleName = cartPage.getInventoryItemName();
		assertEquals(cartArticleName, articleName,"Article does not add to the cart page");
		String priceOfArticle = cartPage.getInventoryItemPrice();
		cartPage.clickCheckoutPageLink();
		checkoutPage.waitForSubHeaderVisible();
		checkoutPage.enterFirstName("Sample Test First Name");
		checkoutPage.enterLastName("Sample Test Last Name");
		checkoutPage.enterPostalCode("123456");
		checkoutPage.clickContinueButton();
		checkoutPage.waitForSubHeaderVisible();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		String priceAtCartSubmissionPage = checkoutPage.getSummarySubTotal();
		assertTrue(priceAtCartSubmissionPage.contains(priceOfArticle),"Article price " + priceOfArticle + " does not match at cart submission page with subtotal price " + priceAtCartSubmissionPage);
	}

	@AfterMethod
	public void afterTest() {
	    driver.quit();
	}
		

}
