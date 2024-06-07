package regres.crud;

import io.restassured.http.ContentType;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.SuccessRegisterResponse;
import org.example.dto.response.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GettingOperationsTest {
    private final String baseUrl = "https://reqres.in/";

    @Test
    public void testGettingListOfUsers() {
        List<UserData> userDataList = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseUrl + "api/users?page=2")
                .then()
                .statusCode(200)
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        for (UserData userData : userDataList) {
            Assertions.assertTrue(userData.getEmail().contains("@reqres.in"));
        }
    }


    @Test
    public void testSuccessfulRegistration() {
        RegisterRequest request = RegisterRequest.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        SuccessRegisterResponse actual = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(baseUrl + "api/register")
                .then()
                .statusCode(200)
                .log().all()
                .extract().as(SuccessRegisterResponse.class);
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", actual.getToken());
    }
}
