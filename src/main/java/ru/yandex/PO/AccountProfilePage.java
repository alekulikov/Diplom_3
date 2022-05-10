package ru.yandex.PO;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AccountProfilePage extends BasePage {

    public static final String ACCOUNT_PROFILE_PAGE_PATH = "/account/profile";

    private SelenideElement exitButton = $(By.xpath("//button[text()='Выход']"));

    public LoginPage clickExitButton() {
        exitButton.click();
        return page(LoginPage.class);
    }
}
