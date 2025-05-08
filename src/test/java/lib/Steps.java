package lib;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pom.ForgotPasswordPage;
import pom.HomePage;
import pom.LoginPage;
import pom.RegisterPage;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Steps {
    private final String expectedErrorMessage = "Некорректный пароль";
    private final String expectedButtonName = "Оформить заказ";

    @Step("Открытие страницы регистрации")
    public void openRegisterPage(RegisterPage registerPage){
        registerPage.openRegisterPage();
    }
    @Step("Страница регистрации. Заполнение поля Имя.")
    public void interUserName(RegisterPage registerPage, String name){
        registerPage.setName(name);
    }
    @Step("Страница регистрации. Заполнение поля Email")
    public void interUserEmail(RegisterPage registerPage, String email){
        registerPage.setEmail(email);
    }
    @Step("Страница регистрации. Заполнение поля Пароль")
    public void interUserPassword(RegisterPage registerPage, String password){
        registerPage.setPassword(password);
    }
    @Step("Страница регистрации. Нажатие на кнопку Зарегистрироваться")
    public void clickToButtonReg(RegisterPage registerPage){
        registerPage.clickRegisterButton();
    }
    @Step("Проверка авторизации созданного пользователя")
    public void checkUserCreation(String email, String password){
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);
        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(loginData)
                .post("https://stellarburgers.nomoreparties.site/api/auth/login");
        response.then().statusCode(200);
    }
    @Step("Страница регистрации. Проверка сообщения об ошибке при вводе некорретного пароля")
    public void checkShortPasswordMessage(RegisterPage registerPage){
        assertEquals(expectedErrorMessage, registerPage.checkShortPasswordMessage());
    }
    @Step("Создание пользователя через API POST /api/auth/register")
    public void postCreateUser(String name, String email, String password){
        Map<String, String> createUserData = new HashMap<>();
        createUserData.put("name", name);
        createUserData.put("email", email);
        createUserData.put("password", password);

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(createUserData)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
    }
    @Step("Страница регистрации. Нажатие кнопки Войти.")
    public void clickToButtonLoginOnRegisterPage(RegisterPage registerPage){
        registerPage.clickLoginButton();
    }

    @Step("Страница логина. Заполнение поля Email")
    public void interUserEmailLoginPage(LoginPage loginPage, String email){
        loginPage.setEmail(email);
    }
    @Step("Страница логина. Заполнение поля Пароль")
    public void interUserPasswordLoginPage(LoginPage loginPage, String password){
        loginPage.setPassword(password);
    }
    @Step("Страница логина. Нажатие на кнопку Войти")
    public void clickToButtonLoginLoginPage(LoginPage loginPage){
        loginPage.clickLoginButton();
    }
    @Step("Главная страница. Проверка названия кнопки")
    public void checkButtonNameHomePage(HomePage homePage){
        assertEquals(expectedButtonName, homePage.getCreateOrderButtonName());
    }
    @Step("Главная страница. Нажатие на кнопку Войти в аккант")
    public void clickToLoginToAccountHomePage(HomePage homePage){
        homePage.clickLoginButton();
    }
    @Step("Открытие главной страницы")
    public void openHomePage(HomePage homePage){
        homePage.openHomePage();
    }
    @Step("Главная страница. Нажатие на кнопку Личный аккаунт")
    public void clickToPersonalAccountHomePage(HomePage homePage){
        homePage.clickPersonalAccount();
    }
    @Step("Открытие страницы восстановления пароль")
    public void openForgotPasswordPagePage(ForgotPasswordPage forgotPasswordPage){
        forgotPasswordPage.openForgotPasswordPage();
    }
    @Step("Страница восстановления пароля. Нажатие на кнопку Войти")
    public void clickLoginButtonForgotPasswordPagePage(ForgotPasswordPage forgotPasswordPage){
        forgotPasswordPage.clickLoginButton();
    }
}
