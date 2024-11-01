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

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @FindBy(className="subheader")
    private WebElement subHeaderWebElement;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNameWebElements;

    @FindBy(className = "inventory_item_price")
    private  List<WebElement> inventoryItemPriceWebElements;

    @FindBy(xpath = "//a[@class='btn_action checkout_button']")
    private WebElement checkoutPageWebElement;

    public void waitForSubHeaderVisible(){
        wait.until(ExpectedConditions.visibilityOf(subHeaderWebElement));
    }

    public String getInventoryItemName(){
        return inventoryItemNameWebElements.get(0).getText();
    }

    public void clickCheckoutPageLink(){
        checkoutPageWebElement.click();
    }

    public String getInventoryItemPrice(){
        return inventoryItemPriceWebElements.get(0).getText();
    }


}
