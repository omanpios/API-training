import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import util.Repo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GithubTests {

    @Test
    void Given_UserIsAuthenticated_When_FoundASpecificRepo_Then_DescriptionIsNotNull() {
        Repo repository = new Repo();
        String description = repository.getDescriptionByRepoName();
        Assertions.assertEquals("Rest assured training", description);
    }
}
