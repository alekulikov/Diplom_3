package ru.yandex;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.BeforeClass;

public abstract class WebDriverSettings {

    @BeforeClass
    public static void initDriver() {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        WebDriverManager.chromedriver().clearDriverCache().setup();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }
}
