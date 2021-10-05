import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Assertions;

import io.restassured.path.json.JsonPath;

public class apiTest {
    public static void main(String[] args){
        RestAssured.baseURI = "https://httpbin.org";
        String response = when().get("/ip").then()
                .assertThat().statusCode(200).extract().response().asString();
        // System.out.println(response);
        JsonPath js = new JsonPath(response);
        String property = js.get("origin");
        Assertions.assertTrue(response.contains(property));
    }
}
