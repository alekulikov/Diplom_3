package ru.yandex.PO;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage extends BasePage {

    public static final String REGISTER_PAGE_PATH = "/register";

    private SelenideElement nameField = $(By.xpath("//label[text()='Имя']//following-sibling::input"));
    private SelenideElement emailField = $(By.xpath("//label[text()='Email']//following-sibling::input"));
    private SelenideElement passwordField = $(By.xpath("//label[text()='Пароль']//following-sibling::input"));
    private SelenideElement registerButton = $(By.xpath("//button[text()='Зарегистрироваться']"));
    private SelenideElement loginButton = $(By.xpath("//a[text()='Войти']"));
    private SelenideElement invalidPasswordMessage = $(By.xpath("//p[text()='Некорректный пароль']"));

    public RegisterPage fillName(String name) {
        nameField.setValue(name);
        return this;
    }

    public RegisterPage fillEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    public RegisterPage fillPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LoginPage clickRegisterButton() {
        registerButton.click();
        return page(LoginPage.class);
    }

    public LoginPage registerUser(String name, String email, String password) {
        return this
                .fillName(name)
                .fillEmail(email)
                .fillPassword(password)
                .clickRegisterButton();
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    public SelenideElement getInvalidPasswordMessage() {
        return invalidPasswordMessage;
    }
}
