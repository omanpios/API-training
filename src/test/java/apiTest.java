import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class apiTest {
    @Test
    void verifyStatusCodeAndProperty() {
        RestAssured.baseURI = "https://httpbin.org";
        String response = when().get("/ip").then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(response);
        String property = js.get("origin");
        Assertions.assertTrue(response.contains(property));
    }
}
