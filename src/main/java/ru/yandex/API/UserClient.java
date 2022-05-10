package ru.yandex.API;

import io.restassured.response.ValidatableResponse;
import ru.yandex.API.model.CreateUserRequest;
import ru.yandex.API.model.LoginUserRequest;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseRestClient {

    private String accessToken;

    public ValidatableResponse createUser(CreateUserRequest request) {
        return given()
                .spec(getBaseSpec())
                .body(request)
                .when()
                .post("api/auth/register")
                .then();
    }

    public ValidatableResponse loginUser(LoginUserRequest request) {
        return given()
                .spec(getBaseSpec())
                .body(request)
                .when()
                .post("api/auth/login")
                .then();
    }

    public ValidatableResponse deleteUser() {
        return accessToken != null
                ? given()
                .spec(getBaseSpec())
                .auth().oauth2(this.accessToken)
                .when()
                .delete("api/auth/user")
                .then()
                : given()
                .spec(getBaseSpec())
                .when()
                .delete("api/auth/user")
                .then();
    }

    public boolean getAccessToken(ValidatableResponse response) {
        try {
            this.accessToken = response.extract().path("accessToken").toString().substring(7);
            return true;
        } catch (NullPointerException e) {
            return false;
        }

    }

    public void flushAccessToken() {
        this.accessToken = null;
    }
}
