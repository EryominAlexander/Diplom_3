package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private By createOrderButton = By.xpath(".//Button[text() = 'Оформить заказ']");
    private By loginToAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private By buttonPersonalAccount = By.xpath(".//p[text()='Личный Кабинет']");
    private By pageTitle = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/h1");
    private By ingredients = By.xpath(".//span[@class='text text_type_main-default']");
    private By constructors = By.xpath(".//span[@class='constructor-element__text']");
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
    public String getTitleName(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(pageTitle));
        return driver.findElement(pageTitle).getText();
    }
    public List<WebElement> getIngredients(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(ingredients));
        return driver.findElements(ingredients);
    }
    public List<WebElement> getConstructors(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(constructors));
        return driver.findElements(constructors);
    }

}
