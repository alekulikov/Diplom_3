package ru.yandex.PO;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public abstract class BasePage {

    private SelenideElement profileButton = $(By.xpath("//p[text()='Личный Кабинет']"));
    private SelenideElement constructorButton = $(By.xpath("//p[text()='Конструктор']"));
    private SelenideElement mainPageBanner = $(By.xpath("//div[contains(@class, 'AppHeader_header__logo')]"));

    public AccountProfilePage clickProfileButton() {
        profileButton.click();
        return page(AccountProfilePage.class);
    }

    public ConstructorPage clickConstructorButton() {
        constructorButton.click();
        return page(ConstructorPage.class);
    }

    public ConstructorPage clickMainPageBanner() {
        mainPageBanner.click();
        return page(ConstructorPage.class);
    }
}
