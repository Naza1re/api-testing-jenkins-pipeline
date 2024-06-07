package regres.crud.bad400;

import io.restassured.http.ContentType;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.BadResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BadRegistrationTest {

    private final String baseUrl = "https://reqres.in/";

    @Test
    @Tag("bad400")
    public void testBadRegistration() {
        RegisterRequest request= RegisterRequest.builder()
                .email("sydney@fife")
                .build();
        BadResponse actual = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(baseUrl+"api/register")
                .then()
                .extract().as(BadResponse.class);
        Assertions.assertEquals("Missing password", actual.getError());
    }
}
