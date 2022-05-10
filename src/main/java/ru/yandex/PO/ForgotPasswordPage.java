package ru.yandex.PO;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage extends BasePage {

    public static final String FORGOT_PASSWORD_PAGE_PATH = "/forgot-password";

    private SelenideElement loginButton = $(By.xpath("//a[text()='Войти']"));

    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }
}
