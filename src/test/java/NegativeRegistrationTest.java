import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lib.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.RegisterPage;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static driver.WebDriverCreator.createWebDriver;


public class NegativeRegistrationTest {
    private String testName;
    private String testEmail;
    private final String shortPassword = "12345";
    private WebDriver driver;

    @Before
    public void start(){
        driver = createWebDriver();
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        testEmail = fakeValuesService.bothify("????##@yandex.ru");
        Matcher emailMatcher = Pattern.compile("\\w{8}\\d{3}@yandex.ru").matcher(testEmail);
        testName = faker.name().firstName();
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