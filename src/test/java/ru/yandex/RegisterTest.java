package ru.yandex;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.API.UserClient;
import ru.yandex.API.UserDataGenerator;
import ru.yandex.API.model.LoginUserRequest;
import ru.yandex.API.model.UserData;
import ru.yandex.PO.LoginPage;
import ru.yandex.PO.RegisterPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@DisplayName("Создание пользователя")
public class RegisterTest extends WebDriverSettings {

    UserClient client;
    UserData user;

    @Before
    public void setUp() {
        closeWebDriver();
        client = new UserClient();
        user = UserDataGenerator.getRandom();
    }

    @After
    public void tearDown() {
        client.getAccessToken(client.loginUser(new LoginUserRequest(user)));
        client.deleteUser();
    }

    @Test
    @DisplayName("Пользователь может быть создан")
    public void userCanBeCreated() {
        open(RegisterPage.REGISTER_PAGE_PATH, RegisterPage.class)
                .registerUser(user.getName(), user.getEmail(), user.getPassword());
        webdriver().shouldHave(url(Configuration.baseUrl + LoginPage.LOGIN_PAGE_PATH));
    }

    @Test
    @DisplayName("Пароль должен быть не менее 6 символов")
    public void passwordMustBeAtLeast6Long() {
        open(RegisterPage.REGISTER_PAGE_PATH, RegisterPage.class)
                .fillPassword("12345")
                .clickRegisterButton();
        page(RegisterPage.class).getInvalidPasswordMessage().shouldBe(visible);
    }
}
