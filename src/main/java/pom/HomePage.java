package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private By createOrderButton = By.xpath(".//Button[text() = 'Оформить заказ']");
    private By loginToAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private By buttonPersonalAccount = By.xpath(".//p[text()='Личный Кабинет']");

    public HomePage (WebDriver driver){
        this.driver = driver;
    }
    public String getCreateOrderButtonName(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(createOrderButton));
        return driver.findElement(createOrderButton).getText();
    }
    public void openHomePage(){
        driver.get("https://stellarburgers.nomoreparties.site");
    }
    public void clickLoginButton(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(loginToAccount));
        driver.findElement(loginToAccount).click();
    }
    public void clickPersonalAccount(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(buttonPersonalAccount));
        driver.findElement(buttonPersonalAccount).click();
    }

}
