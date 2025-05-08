import json.CreatedUserData;
import lib.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.ForgotPasswordPage;
import pom.HomePage;
import pom.LoginPage;
import pom.RegisterPage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserLoginTest {
    private String testName;
    private String testEmail;
    private String testPassword;
    private WebDriver driver;


    @Before
    public void start(){
        driver = new ChromeDriver();
        Random random = new Random();
        testEmail = "testEmail" + random.nextInt(1000000) + "@yandex.ru";
        testName = "testName" + random.nextInt(1000000);
        testPassword = "testPassword" + random.nextInt(1000000);
    }
    @Test
    public void loginFromRegisterPage(){
        Steps steps = new Steps();
        steps.postCreateUser(testName, testEmail, testPassword);

        RegisterPage registerPage = new RegisterPage(driver);

        steps.openRegisterPage(registerPage);
        steps.clickToButtonLoginOnRegisterPage(registerPage);

        LoginPage loginPage = new LoginPage(driver);

        steps.interUserEmailLoginPage(loginPage, testEmail);
        steps.interUserPasswordLoginPage(loginPage, testPassword);
        steps.clickToButtonLoginLoginPage(loginPage);

        HomePage homePage = new HomePage(driver);
        steps.checkButtonNameHomePage(homePage);

    }
    @Test
    public void loginFromHomePageButtonLoginToAccount(){
        Steps steps = new Steps();
        steps.postCreateUser(testName, testEmail, testPassword);

        HomePage homePage = new HomePage(driver);
        steps.openHomePage(homePage);
        steps.clickToLoginToAccountHomePage(homePage);

        LoginPage loginPage = new LoginPage(driver);

        steps.interUserEmailLoginPage(loginPage, testEmail);
        steps.interUserPasswordLoginPage(loginPage, testPassword);
        steps.clickToButtonLoginLoginPage(loginPage);

        steps.checkButtonNameHomePage(homePage);
    }
    @Test
    public void loginFromHomePageButtonPersonalAccount(){
        Steps steps = new Steps();
        steps.postCreateUser(testName, testEmail, testPassword);

        HomePage homePage = new HomePage(driver);
        steps.openHomePage(homePage);
        steps.clickToPersonalAccountHomePage(homePage);

        LoginPage loginPage = new LoginPage(driver);

        steps.interUserEmailLoginPage(loginPage, testEmail);
        steps.interUserPasswordLoginPage(loginPage, testPassword);
        steps.clickToButtonLoginLoginPage(loginPage);

        steps.checkButtonNameHomePage(homePage);
    }

    @Test
    public void loginFromForgotPasswordPage(){
        Steps steps = new Steps();
        steps.postCreateUser(testName, testEmail, testPassword);

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        steps.openForgotPasswordPagePage(forgotPasswordPage);
        steps.clickLoginButtonForgotPasswordPagePage(forgotPasswordPage);

        LoginPage loginPage = new LoginPage(driver);

        steps.interUserEmailLoginPage(loginPage, testEmail);
        steps.interUserPasswordLoginPage(loginPage, testPassword);
        steps.clickToButtonLoginLoginPage(loginPage);

        HomePage homePage = new HomePage(driver);
        steps.checkButtonNameHomePage(homePage);
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
