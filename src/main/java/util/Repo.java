package util;

import java.util.List;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class Repo {
    JsonPath jsonPath;

    public Repo() {
        RestAssured.baseURI = "https://api.github.com";
        String accessToken = System.getenv("accessToken");
        String response = given().auth().oauth2(accessToken).when().get("user/repos").then().assertThat()
                .statusCode(200).extract().response().asString();
        jsonPath = new JsonPath(response);
    }

    public String getDescriptionByRepoName() {
        List<String> repos = jsonPath.get("name");
        String repoName = "API";
        String getRepo = repos.stream().filter(name -> name.contains(repoName)).findFirst().get();
        int index = repos.indexOf(getRepo);
        String description = jsonPath.get("description[" + index + "]");
        return description;
    }
}
