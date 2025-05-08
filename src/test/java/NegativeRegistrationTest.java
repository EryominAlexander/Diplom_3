import lib.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.RegisterPage;
import java.util.Random;


public class NegativeRegistrationTest {
    private String testName;
    private String testEmail;
    private final String shortPassword = "12345";
    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
        Random random = new Random();
        testEmail = "testEmail" + random.nextInt(1000000) + "@yandex.ru";
        testName = "testName" + random.nextInt(1000000);
    }
    @Test
    public void registrationWithShortPasswordTest(){
        RegisterPage registerPage = new RegisterPage(driver);
        Steps steps = new Steps();

        steps.openRegisterPage(registerPage);
        steps.interUserName(registerPage, testName);
        steps.interUserEmail(registerPage, testEmail);
        steps.interUserPassword(registerPage, shortPassword);
        steps.clickToButtonReg(registerPage);
        steps.checkShortPasswordMessage(registerPage);
    }
    @After
    public void close(){
        driver.quit();
    }
}