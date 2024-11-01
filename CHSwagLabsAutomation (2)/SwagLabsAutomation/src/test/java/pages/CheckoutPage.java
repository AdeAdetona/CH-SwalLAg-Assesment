package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @FindBy(className="subheader")
    private WebElement subHeaderWebElement;

    @FindBy(id = "first-name")
    private WebElement firstNameWebElement;

    @FindBy(id = "last-name")
    private WebElement lastNameWebElement;

    @FindBy(id = "postal-code")
    private WebElement postalCodeWebElement;

    @FindBy(xpath = "//input[@class='btn_primary cart_button']")
    private WebElement continueButtonWebElement;

    @FindBy(xpath = "//a[@class='btn_action cart_button']")
    private WebElement buttonActionWebElement;

    @FindBy(className = "complete-header")
    private WebElement headerWebElement;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessageWebElement;

    @FindBy(className = "summary_subtotal_label")
    private WebElement summarySubTotalWebElement;

    public void waitForSubHeaderVisible(){
        wait.until(ExpectedConditions.visibilityOf(subHeaderWebElement));
    }

    public void enterFirstName(String value) {
        firstNameWebElement.sendKeys(value);
    }

    public void enterLastName(String value) {
        lastNameWebElement.sendKeys(value);
    }
    public void enterPostalCode(String value) {
        postalCodeWebElement.sendKeys(value);
    }

    public void clickContinueButton(){
        continueButtonWebElement.click();
    }

    public void clickbuttonAction(){
        buttonActionWebElement.click();
    }

    public String getHeaderValue(){
        return headerWebElement.getText();
    }

    public String getErrorMessage(){
        return errorMessageWebElement.getText();
    }

    public String getSummarySubTotal(){
        return summarySubTotalWebElement.getText();
    }
}
