package ru.yandex.API;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.API.model.UserData;

public class UserDataGenerator {

    public static UserData getRandom() {
        return getRandom(10, 10, 10);
    }

    public static UserData getRandom(int lengthEmail, int lengthPassword, int lengthName) {
        String email = RandomStringUtils.randomAlphabetic(lengthEmail) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(lengthPassword);
        String name = RandomStringUtils.randomAlphabetic(lengthName);

        return new UserData(email, password, name);
    }
}
