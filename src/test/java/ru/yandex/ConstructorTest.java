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
import ru.yandex.PO.ConstructorPage;
import ru.yandex.PO.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@DisplayName("Конструктор")
public class ConstructorTest extends WebDriverSettings {
    
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
    @DisplayName("Нажатие на селектор открывает блок с булками")
    public void pressingTheSelectorOpensABlockWithBuns() {
        open(ConstructorPage.CONSTRUCTOR_PAGE_PATH, ConstructorPage.class)
                .selectSauces()
                .selectBuns()
                .getBunsHeader().shouldBe(visible);
    }

    @Test
    @DisplayName("Нажатие на селектор открывает блок с соусами")
    public void pressingTheSelectorOpensABlockWithSauces() {
        open(ConstructorPage.CONSTRUCTOR_PAGE_PATH, ConstructorPage.class)
                .selectSauces()
                .getSaucesHeader().shouldBe(visible);
    }

    @Test
    @DisplayName("Нажатие на селектор открывает блок с начинкой")
    public void pressingTheSelectorOpensABlockWithFillings() {
        open(ConstructorPage.CONSTRUCTOR_PAGE_PATH, ConstructorPage.class)
                .selectFillings()
                .getFillingsHeader().shouldBe(visible);
    }

    @Test
    @DisplayName("При нажатии на кнопку происходит переход на страницу конструктора")
    public void clickingTheButtonTakesYouToConstructorPage() {
        open(LoginPage.LOGIN_PAGE_PATH, LoginPage.class).clickConstructorButton();
        webdriver().shouldHave(url(Configuration.baseUrl + ConstructorPage.CONSTRUCTOR_PAGE_PATH));
    }

    @Test
    @DisplayName("При нажатии на логотип сайта происходит переход на страницу конструктора")
    public void clickingTheLogoTakesYouToConstructorPage() {
        open(LoginPage.LOGIN_PAGE_PATH, LoginPage.class).clickMainPageBanner();
        webdriver().shouldHave(url(Configuration.baseUrl + ConstructorPage.CONSTRUCTOR_PAGE_PATH));
    }
}