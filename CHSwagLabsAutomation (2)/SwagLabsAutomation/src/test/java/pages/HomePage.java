package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @FindBy(className="inventory_item_name")
    private List<WebElement> inventroyItemNameWebElement;

    @FindBy(xpath="//button[@class='btn_primary btn_inventory']")
    private List<WebElement> addArticleWebElement;

    @FindBy(xpath="//span[@class='fa-layers-counter shopping_cart_badge']")
    private WebElement cartSizeWebElement;

    @FindBy(id="shopping_cart_container")
    private WebElement shoppingCartContainerWebElement;

    private By productLabelByElement = By.className("product_label");

    public void waitForProductLabelToBeVisible(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLabelByElement));
    }

    public String getArticleName(){
        return inventroyItemNameWebElement.get(0).getText();
    }

    public void clickAddArticle(){
        addArticleWebElement.get(0).click();
    }

    public String cartSize(){
        return cartSizeWebElement.getText();
    }
    public void clickShoppingCartContainer(){
        shoppingCartContainerWebElement.click();
    }
}
