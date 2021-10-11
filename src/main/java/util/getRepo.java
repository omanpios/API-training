package util;

import org.junit.jupiter.api.Assertions;

import io.restassured.path.json.JsonPath;

public class getRepo {

    public void validateDescriptionIsNotNull(String response) {
        JsonPath js = new JsonPath(response);
        int totalRepos = js.getInt("name.size()");

        for (int i = 0; i < totalRepos; i++) {
            String repoName = js.get("name[" + i + "]");

            if (repoName.equals("API-training")) {
                String description = js.get("description[" + i + "]");
                Assertions.assertNotNull(description);
            }
        }
    }

}
