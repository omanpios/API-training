import org.apache.struts.taglib.logic.NotEmptyTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
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

    @Test
    void verifyQueryParams() {
        RestAssured.baseURI = "https://httpbin.org";
        String response = given().queryParam("test", "1234").queryParam("user", "opinilla").when()
                .get("response-headers").then().assertThat().statusCode(200).assertThat().extract().response()
                .asString();
        JsonPath js = new JsonPath(response);
        String test = js.get("test");
        String user = js.get("user");
        Assertions.assertEquals(test, "1234");
        Assertions.assertEquals(user, "opinilla");

    }

}
