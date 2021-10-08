import org.hamcrest.Matchers;
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

    @Test
    void verifyQueryParams() {
        RestAssured.baseURI = "https://httpbin.org";
        given().queryParam("test", "1234").queryParam("user", "opinilla").when().get("response-headers").then()
                .assertThat().statusCode(200).body("test", Matchers.is("1234")).body("user", Matchers.is("opinilla"));

    }

    @Test
    void Given_UserIsAuthenticated_When_FoundASpecificRepo_Then_DescriptionIsNotNull() {
        RestAssured.baseURI = "https://api.github.com";
        String accesstoken = System.getenv("accessToken");
        String response = given().auth().oauth2(
                accesstoken).when().get("user/repos").then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        int totalRepos = js.getInt("name.size()");

        for (int i = 0; i < totalRepos; i++) {
            String repoName = js.get("name[" + i + "]");

            if (repoName.equals("API-training")) {
                String description = js.get("description[" + i + "]");
                Assertions.assertNotNull(description);
                System.out.println(repoName + ": " + description);
            }
        }
    }
}
