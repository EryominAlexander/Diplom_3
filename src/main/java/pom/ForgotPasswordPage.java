package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    WebDriver driver;
    private By loginButton = By.xpath(".//a[@href = '/login']");

    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
    }
    public void openForgotPasswordPage(){
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }
    public void clickLoginButton(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }
}
