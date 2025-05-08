package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    WebDriver driver;
    private final By profileButton = By.xpath(".//a[@href = '/account/profile' ]");
    private final By profileHistoryButton = By.xpath(".//a[@href = '/account/order-history']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By userName = By.xpath(".//label[ text()='Имя' ]/parent::div/input");
    private final By userEmail = By.xpath(".//label[ text()='Логин' ]/parent::div/input");
    private final By userPassword = By.xpath(".//input[@type='password']");
    private final By infoText = By.xpath(".//nav[@class = 'Account_nav__Lgali']/p");

    public PersonalAccountPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickExit(){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }
    public String getUserName(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(userName));
        return driver.findElement(userName).getAttribute("defaultValue");
    }
    public String getUserEmail(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(userEmail));
        return driver.findElement(userEmail).getAttribute("defaultValue");
    }
    public String getUserPassword(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(userPassword));
        return driver.findElement(userPassword).getAttribute("defaultValue");
    }
    public String getInfoText(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(infoText));
        return driver.findElement(infoText).getAttribute("textContent");
    }
    public boolean checkProfileButton(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(profileButton));
        if( driver.findElement(profileButton) != null )
            return true;
        else
            return false;
    }
    public boolean checkHistoryButton(){
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(profileHistoryButton));
        if( driver.findElement(profileHistoryButton) != null )
            return true;
        else
            return false;
    }
}
