package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    @FindBy(id="user-name")
    private WebElement usernameWebElement;

    @FindBy(id="password")
    private WebElement passwordWebElement;

    @FindBy(id="login-button")
    private WebElement loginButtonWebElement;

    public void enterUsername(String username){
        wait.until(ExpectedConditions.visibilityOf(usernameWebElement));
        usernameWebElement.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordWebElement.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButtonWebElement.click();
    }

}
