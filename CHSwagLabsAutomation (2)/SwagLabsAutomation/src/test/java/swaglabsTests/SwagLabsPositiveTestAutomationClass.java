package swaglabsTests;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class SwagLabsPositiveTestAutomationClass {
	
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
		loginPage.enterUsername(SwagLabsConstants.STANDARD_USERNAME);
		loginPage.enterPassword(SwagLabsConstants.PASSWORD);
		loginPage.clickLoginButton();
		homePage.waitForProductLabelToBeVisible();
	}
	
	@Test
	public void validateUserIsAbleToPlaceOrder() {
		String articleName = homePage.getArticleName();
		homePage.clickAddArticle();
		String cartSize = homePage.cartSize();
		assertEquals(cartSize, "1","Article does not add to the cart page");
		homePage.clickShoppingCartContainer();
		cartPage.waitForSubHeaderVisible();
		String cartArticleName = cartPage.getInventoryItemName();
		assertEquals(cartArticleName, articleName,"Article does not add to the cart page");
		cartPage.clickCheckoutPageLink();
		checkoutPage.waitForSubHeaderVisible();
		checkoutPage.enterFirstName("Sample Test First Name");
		checkoutPage.enterLastName("Sample Test Last Name");
		checkoutPage.enterPostalCode("123456");
		checkoutPage.clickContinueButton();
		checkoutPage.waitForSubHeaderVisible();
		checkoutPage.clickbuttonAction();
		checkoutPage.waitForSubHeaderVisible();
		String actualOrderPlacedMessage = checkoutPage.getHeaderValue();
		assertEquals(actualOrderPlacedMessage,"THANK YOU FOR YOUR ORDER","Order does not placed successfully");
	}

	@Test
	public void validateFirstNameIsMandatoryField() {
		String articleName = homePage.getArticleName();
		homePage.clickAddArticle();
		String cartSize = homePage.cartSize();
		assertEquals(cartSize, "1","Article does not add to the cart page");
		homePage.clickShoppingCartContainer();
		cartPage.waitForSubHeaderVisible();
		String cartArticleName = cartPage.getInventoryItemName();
		assertEquals(cartArticleName, articleName,"Article does not add to the cart page");
		cartPage.clickCheckoutPageLink();
		checkoutPage.waitForSubHeaderVisible();
		checkoutPage.clickContinueButton();
		String actualErrorMessage = checkoutPage.getErrorMessage();
		assertTrue(actualErrorMessage.contains("First Name is required"),"First name is not a Mandatory Field");
	}

	@Test
	public void validateLastNameIsMandatoryField() {
		String articleName = homePage.getArticleName();
		homePage.clickAddArticle();
		String cartSize = homePage.cartSize();
		assertEquals(cartSize, "1","Article does not add to the cart page");
		homePage.clickShoppingCartContainer();
		cartPage.waitForSubHeaderVisible();
		String cartArticleName = cartPage.getInventoryItemName();
		assertEquals(cartArticleName, articleName,"Article does not add to the cart page");
		cartPage.clickCheckoutPageLink();
		checkoutPage.waitForSubHeaderVisible();
		checkoutPage.enterFirstName("Sample Test First Name");
		checkoutPage.clickContinueButton();
		String actualErrorMessage = checkoutPage.getErrorMessage();
		assertTrue(actualErrorMessage.contains("Last Name is required"),"Last name is not a Mandatory Field");
	}


	@Test
	public void validatePostalCodeIsMandatoryField() {
		String articleName = homePage.getArticleName();
		homePage.clickAddArticle();
		String cartSize = homePage.cartSize();
		assertEquals(cartSize, "1","Article does not add to the cart page");
		homePage.clickShoppingCartContainer();
		cartPage.waitForSubHeaderVisible();
		String cartArticleName = cartPage.getInventoryItemName();
		assertEquals(cartArticleName, articleName,"Article does not add to the cart page");
		cartPage.clickCheckoutPageLink();
		checkoutPage.waitForSubHeaderVisible();
		checkoutPage.enterFirstName("Sample Test First Name");
		checkoutPage.enterLastName("Sample Test Last Name");
		checkoutPage.clickContinueButton();
		String actualErrorMessage = checkoutPage.getErrorMessage();
		assertTrue(actualErrorMessage.contains("Postal Code is required"),"Postal Code is not a Mandatory Field");
	}

	@AfterMethod
	public void afterTest() {
	    driver.quit();
	}
		

}
