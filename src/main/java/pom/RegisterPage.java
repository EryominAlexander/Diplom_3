package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private final WebDriver driver;
    private final By inputName = By.xpath(".//label[ text()='Имя' ]/parent::div/input");
    private final By inputEmail = By.xpath(".//label[ text()='Email' ]/parent::div/input");
    private final By inputPassword = By.xpath(".//input[@type='password']");
    private final By buttonRegister = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By messageErrorShortPassword = By.xpath(".//p[text() = 'Некорректный пароль' ]");
    private final By buttonLogin = By.xpath(".//a[text() = 'Войти' ]");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void openRegisterPage(){
        driver.get("http://stellarburgers.nomoreparties.site/register");
    }

    public void setName(String name){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(inputName));
        driver.findElement(inputName).sendKeys(name);
    }
    public void setEmail(String email){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(inputEmail));
        driver.findElement(inputEmail).sendKeys(email);
    }
    public void setPassword(String password){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(inputPassword));
        driver.findElement(inputPassword).sendKeys(password);
    }
    public void clickRegisterButton(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(buttonRegister));
        driver.findElement(buttonRegister).click();
    }
    public String checkShortPasswordMessage(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(messageErrorShortPassword));
        return driver.findElement(messageErrorShortPassword).getText();
    }
    public void clickLoginButton(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(buttonLogin));
        driver.findElement(buttonLogin).click();
    }
}
