package learn.reactive.react.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import learn.reactive.react.domain.MovieInfo;
import reactor.test.StepVerifier;

@DataMongoTest
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class MovieInfoRepositoryIntgTest {

    @Autowired
    MovieInfoRepository movieInfoRepository;

    @BeforeEach
    void setUp() {
        var moviesInfo = List.of(
                new MovieInfo("1", "Pushpa: The rise",
                        List.of("Allu Arjun", "Manduka", "Fahad"), 2021,
                        LocalDate.parse("2021-12-17")),
                new MovieInfo("2", "Pushpa: The rule",
                        List.of("Allu Arjun", "Manduka", "Fahad"), 2022,
                        LocalDate.parse("2022-12-17")));

        movieInfoRepository.saveAll(moviesInfo).blockLast();
    }

    @AfterEach
    void Clean() {
        movieInfoRepository.deleteAll().block();
    }

    @Test
    void testFindAll() {
        var list = movieInfoRepository.findAll();
        StepVerifier.create(list).expectNextCount(2).verifyComplete();
    }

    @Test
    void testFindById() {
        var movie = movieInfoRepository.findById("2");
        StepVerifier.create(movie)
                .assertNext(movieInfo -> {
                    assertEquals(2022, movieInfo.getYear());
                });
    }

    @Test
    void testSaveMovieInfo() {
        var movie = movieInfoRepository.save(new MovieInfo("3", "KGF",
                List.of("Rocky", "Neel"), 2022,
                LocalDate.parse("2022-04-14")));
        StepVerifier.create(movie).assertNext(movieInfo -> {
            assertEquals("Rocky", movieInfo.getCast().get(0));
        });
    }
}
