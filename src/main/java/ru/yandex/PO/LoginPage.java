package ru.yandex.PO;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage {

    public static final String LOGIN_PAGE_PATH = "/login";

    private SelenideElement emailField = $(By.xpath("//label[text()='Email']//following-sibling::input"));
    private SelenideElement passwordField = $(By.xpath("//label[text()='Пароль']//following-sibling::input"));
    private SelenideElement loginButton = $(By.xpath("//button[text()='Войти']"));

    public LoginPage fillEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    public LoginPage fillPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public ConstructorPage clickLoginButton() {
        loginButton.click();
        return page(ConstructorPage.class);
    }

    public ConstructorPage loginUser(String email, String password) {
        return this
                .fillEmail(email)
                .fillPassword(password)
                .clickLoginButton();
    }
}
