package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final By pageName = By.xpath(".//h2[text()='Вход']");
    private final By emailInput = By.xpath(".//label[text() = 'Email' ]/parent::div/input");
    private final By passwordInput = By.xpath(".//input[@name ='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void openLoginPage(){
        driver.get("http://stellarburgers.nomoreparties.site/login");
    }
    public String getPageName(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(pageName));
        return driver.findElement(pageName).getText();
    }
    public void setEmail(String email){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(emailInput));
        driver.findElement(emailInput).sendKeys(email);
    }
    public void setPassword(String password){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void clickLoginButton(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }
}
