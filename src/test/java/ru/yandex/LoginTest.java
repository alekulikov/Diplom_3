package ru.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.API.UserClient;
import ru.yandex.API.UserDataGenerator;
import ru.yandex.API.model.CreateUserRequest;
import ru.yandex.API.model.LoginUserRequest;
import ru.yandex.API.model.UserData;
import ru.yandex.PO.ConstructorPage;
import ru.yandex.PO.ForgotPasswordPage;
import ru.yandex.PO.LoginPage;
import ru.yandex.PO.RegisterPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Авторизация пользователя")
public class LoginTest extends WebDriverSettings {

    UserClient client;
    UserData user;

    @Before
    public void setUp() {
        closeWebDriver();
        client = new UserClient();
        user = UserDataGenerator.getRandom();
        client.createUser(new CreateUserRequest(user));
    }

    @After
    public void tearDown() {
        client.getAccessToken(client.loginUser(new LoginUserRequest(user)));
        client.deleteUser();
    }

    @Test
    @DisplayName("Пользователь может авторизоваться через кнопку войти в аккаунт")
    public void userCanLogInUsingButton() {
        open(ConstructorPage.CONSTRUCTOR_PAGE_PATH, ConstructorPage.class)
                .clickLoginButton()
                .loginUser(user.getEmail(), user.getPassword())
                .getCreateOrderButton().shouldBe(visible);
    }

    @Test
    @DisplayName("Пользователь может авторизоваться через личный кабинет")
    public void userCanLogInThroughThePersonalAccount() {
        open(ConstructorPage.CONSTRUCTOR_PAGE_PATH, ConstructorPage.class).clickProfileButton();
        page(LoginPage.class).loginUser(user.getEmail(), user.getPassword())
                .getCreateOrderButton().shouldBe(visible);
    }

    @Test
    @DisplayName("Пользователь может авторизоваться через кнопку в форме регистрации")
    public void userCanLogInThroughTheButtonInTheRegistrationForm() {
        open(RegisterPage.REGISTER_PAGE_PATH, RegisterPage.class)
                .clickLoginButton()
                .loginUser(user.getEmail(), user.getPassword())
                .getCreateOrderButton().shouldBe(visible);
    }

    @Test
    @DisplayName("Пользователь может авторизоваться через кнопку в форме восстановления пароля")
    public void userCanLogInThroughTheButtonInThePasswordRecoveryForm() {
        open(ForgotPasswordPage.FORGOT_PASSWORD_PAGE_PATH, ForgotPasswordPage.class)
                .clickLoginButton()
                .loginUser(user.getEmail(), user.getPassword())
                .getCreateOrderButton().shouldBe(visible);
    }
}
