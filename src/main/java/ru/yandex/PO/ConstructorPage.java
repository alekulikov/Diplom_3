package ru.yandex.PO;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ConstructorPage extends BasePage {

    public static final String CONSTRUCTOR_PAGE_PATH = "/";

    private SelenideElement loginButton = $(By.xpath("//button[text()='Войти в аккаунт']"));
    private SelenideElement bunsSelector = $(By.xpath(".//span[text()='Булки']")) ;
    private SelenideElement saucesSelector = $(By.xpath(".//span[text()='Соусы']")) ;
    private SelenideElement fillingsSelector = $(By.xpath(".//span[text()='Начинки']")) ;
    private SelenideElement bunsHeader = $(By.xpath(".//h2[text()='Булки']"));
    private SelenideElement saucesHeader = $(By.xpath(".//h2[text()='Соусы']"));
    private SelenideElement fillingsHeader = $(By.xpath(".//h2[text()='Начинки']"));
    private SelenideElement createOrderButton = $(By.xpath("//button[text()='Оформить заказ']"));

    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    public ConstructorPage selectBuns() {
        bunsSelector.click();
        return this;
    }

    public ConstructorPage selectSauces() {
        saucesSelector.click();
        return this;
    }

    public ConstructorPage selectFillings() {
        fillingsSelector.click();
        return this;
    }

    public SelenideElement getBunsHeader() {
        return bunsHeader;
    }

    public SelenideElement getSaucesHeader() {
        return saucesHeader;
    }

    public SelenideElement getFillingsHeader() {
        return fillingsHeader;
    }

    public SelenideElement getCreateOrderButton() {
        return createOrderButton;
    }
}
