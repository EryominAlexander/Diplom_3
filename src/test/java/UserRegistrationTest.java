import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import json.CreatedUserData;
import lib.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.LoginPage;
import pom.RegisterPage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static driver.WebDriverCreator.createWebDriver;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UserRegistrationTest {
    private String testName;
    private String testEmail;
    private String testPassword;
    private WebDriver driver;
    private final String expectedPageName = "Вход";


    @Before
    public void start(){
        driver = createWebDriver();
        Random random = new Random();
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        testEmail = fakeValuesService.bothify("????##@yandex.ru");
        Matcher emailMatcher = Pattern.compile("\\w{8}\\d{3}@yandex.ru").matcher(testEmail);
        testName = faker.name().firstName();
        testPassword = "testPassword" + random.nextInt(1000000);
    }
    @Test
    public void registrationUserTest(){

        RegisterPage registerPage = new RegisterPage(driver);
        Steps steps = new Steps();

        steps.openRegisterPage(registerPage);
        steps.interUserName(registerPage, testName);
        steps.interUserEmail(registerPage, testEmail);
        steps.interUserPassword(registerPage, testPassword);
        steps.clickToButtonReg(registerPage);

        LoginPage loginPage = new LoginPage(driver);
        assertEquals(expectedPageName, loginPage.getPageName());

        steps.checkUserCreation(testEmail, testPassword);
    }
    @After
    public void close(){
        driver.quit();

        Map<String, String> loginUserData = new HashMap<>();
        loginUserData.put("email", testEmail);
        loginUserData.put("password", testPassword);

        CreatedUserData createdUserData = given()
                .header("Content-Type", "application/json")
                .and()
                .body(loginUserData)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .as(CreatedUserData.class);

        given()
                .header("Content-Type", "application/json")
                .and()
                .header("Authorization",createdUserData.getAccessToken())
                .when()
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
    }

}
