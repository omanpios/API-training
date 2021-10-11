import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import io.restassured.RestAssured;
import util.getRepo;

import static io.restassured.RestAssured.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class githubTest {

    private getRepo GetRepo;

    @BeforeAll
    void setUP() {
        GetRepo = new getRepo();
    }

    @Test
    void Given_UserIsAuthenticated_When_FoundASpecificRepo_Then_DescriptionIsNotNull() {
        RestAssured.baseURI = "https://api.github.com";
        String accesstoken = System.getenv("accessToken");
        String response = given().auth().oauth2(accesstoken).when().get("user/repos").then().assertThat()
                .statusCode(200).extract().response().asString();
        GetRepo.validateDescriptionIsNotNull(response);
    }
}
