package ru.yandex;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.API.UserClient;
import ru.yandex.API.UserDataGenerator;
import ru.yandex.API.model.CreateUserRequest;
import ru.yandex.API.model.LoginUserRequest;
import ru.yandex.API.model.UserData;
import ru.yandex.PO.AccountProfilePage;
import ru.yandex.PO.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@DisplayName("Личный кабинет пользователя")
public class AccountProfileTest extends WebDriverSettings {
    
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
    @DisplayName("Пользователь может перейти в личный кабинет с главной страницы")
    public void fromConstructorPageUserCanGetToPersonalAccount() {
        open(LoginPage.LOGIN_PAGE_PATH, LoginPage.class)
                .loginUser(user.getEmail(), user.getPassword())
                .clickProfileButton();
        webdriver().shouldHave(url(Configuration.baseUrl + AccountProfilePage.ACCOUNT_PROFILE_PAGE_PATH));
    }

    @Test
    @DisplayName("Пользователь может выйти из аккаунта в личном кабинете")
    public void userCanLogOutInThePersonalAccountPage() {
        open(LoginPage.LOGIN_PAGE_PATH, LoginPage.class)
                .loginUser(user.getEmail(), user.getPassword())
                .clickProfileButton()
                .clickExitButton();
        webdriver().shouldHave(url(Configuration.baseUrl + LoginPage.LOGIN_PAGE_PATH));
    }
}
