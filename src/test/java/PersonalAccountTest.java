import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import json.CreatedUserData;
import lib.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pom.HomePage;
import pom.LoginPage;
import pom.PersonalAccountPage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static driver.WebDriverCreator.createWebDriver;
import static io.restassured.RestAssured.given;

public class PersonalAccountTest {
    private String testName;
    private String testEmail;
    private String testPassword;
    private WebDriver driver;


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
    public void checkPersonalAccountTest(){
        Steps steps = new Steps();
        steps.postCreateUser(testName, testEmail, testPassword);

        HomePage homePage = new HomePage(driver);
        steps.openHomePage(homePage);
        steps.clickToLoginToAccountHomePage(homePage);

        LoginPage loginPage = new LoginPage(driver);

        steps.interUserEmailLoginPage(loginPage, testEmail);
        steps.interUserPasswordLoginPage(loginPage, testPassword);
        steps.clickToButtonLoginLoginPage(loginPage);

        steps.clickToPersonalAccountHomePage(homePage);

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        steps.profileButtonExists(personalAccountPage);
        steps.historyButtonExists(personalAccountPage);
        steps.infoTextExists(personalAccountPage);
        steps.checkUserNameAccount(personalAccountPage, testName);
        steps.checkUserEmailAccount(personalAccountPage, testEmail);
        steps.checkUserPasswordAccount(personalAccountPage);
    }
    @Test
    public void exitFromPersonalAccountTest(){
        Steps steps = new Steps();
        steps.postCreateUser(testName, testEmail, testPassword);

        HomePage homePage = new HomePage(driver);
        steps.openHomePage(homePage);
        steps.clickToLoginToAccountHomePage(homePage);

        LoginPage loginPage = new LoginPage(driver);

        steps.interUserEmailLoginPage(loginPage, testEmail);
        steps.interUserPasswordLoginPage(loginPage, testPassword);
        steps.clickToButtonLoginLoginPage(loginPage);

        steps.clickToPersonalAccountHomePage(homePage);

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        steps.profileButtonExists(personalAccountPage);
        steps.clickExitAccount(personalAccountPage);
        steps.getPageNameLoginPage(loginPage);
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
