package lib;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import pom.*;

import javax.lang.model.element.Element;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Steps {
    private final String expectedErrorMessage = "Некорректный пароль";
    private final String expectedButtonName = "Оформить заказ";
    private final String expectedInfoText = "В этом разделе вы можете изменить свои персональные данные";
    private final String expectedPassword = "*****";
    private final String expectedLoginPageName = "Вход";
    private final String expectedTitleNameHomePage = "Соберите бургер";
    private final  List<String> expectedIngredients = Arrays.asList("Булки", "Соусы", "Начинки");
    private final  List<String> expectedConstructors= Arrays.asList("Перетяните булочку сюда (верх)", "Перетяните булочку сюда (низ)");

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
    @Step("Страница личного кабинета. Проверка наличия кнопки Профиль")
    public void profileButtonExists(PersonalAccountPage personalAccountPage){
        assertTrue(personalAccountPage.checkProfileButton());
    }
    @Step("Страница личного кабинета. Проверка наличия кнопки История заказов")
    public void historyButtonExists(PersonalAccountPage personalAccountPage){
        assertTrue(personalAccountPage.checkHistoryButton());
    }
    @Step("Страница личного кабинета. Проверка наличия информационного текста")
    public void infoTextExists(PersonalAccountPage personalAccountPage){
        assertEquals(expectedInfoText, personalAccountPage.getInfoText());
    }
    @Step("Страница личного кабинета. Проверка отображения имени пользователя")
    public void checkUserNameAccount(PersonalAccountPage personalAccountPage, String name){
        assertEquals(name, personalAccountPage.getUserName());
    }
    @Step("Страница личного кабинета. Проверка отображения логина пользователя")
    public void checkUserEmailAccount(PersonalAccountPage personalAccountPage, String email){
        assertEquals(email.toLowerCase(), personalAccountPage.getUserEmail());
    }
    @Step("Страница личного кабинета. Проверка отображения пароля пользователя")
    public void checkUserPasswordAccount(PersonalAccountPage personalAccountPage){
        assertEquals(expectedPassword, personalAccountPage.getUserPassword());
    }
    @Step("Страница личного кабинета. Нажатие на кнопку Выход")
    public void clickExitAccount(PersonalAccountPage personalAccountPage){
        personalAccountPage.clickExit();
    }
    @Step("Страница логина. Получение названия страницы")
    public void getPageNameLoginPage(LoginPage loginPage){
        assertEquals(expectedLoginPageName, loginPage.getPageName());
    }
    @Step("Страница личного кабинета. Нажатие на кнопку Конструктор")
    public void clickConstructorAccount(PersonalAccountPage personalAccountPage){
        personalAccountPage.clickConstructor();
    }
    @Step("Страница личного кабинета. Нажатие на Логотип")
    public void clickLogoAccount(PersonalAccountPage personalAccountPage){
        personalAccountPage.clickLogo();
    }
    @Step("Главная страница. Проверка названия страницы")
    public void checkTitleNameHomePage(HomePage homePage){
        assertEquals(expectedTitleNameHomePage, homePage.getTitleName());
    }
    @Step("Главная страница. Проверка списка игредиентов")
    public void checkIngredientListHomePage(HomePage homePage){
        List<String> ingredients = new ArrayList<>();
        for (WebElement element : homePage.getIngredients()){
            ingredients.add(element.getText());
        }
        assertEquals(expectedIngredients, ingredients);
    }
    @Step("Главная страница. Проверка списка конструкторов")
    public void checkConstructorsListHomePage(HomePage homePage){
        List<String> constructors = new ArrayList<>();

        for (WebElement element : homePage.getConstructors()){
            constructors.add(element.getText());
        }
        assertEquals(expectedConstructors, constructors);
    }
}
