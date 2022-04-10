package learn.reactive.react.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@WebFluxTest(controllers = BaseController.class)
@AutoConfigureWebTestClient
class BaseControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void helloWorld() {
        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .isEqualTo("Hello World");

    }

    @Test
    void helloWorldApproach2() {
        var approach2  = webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(String.class).getResponseBody();
        StepVerifier.create(approach2).expectNext("Hello World").verifyComplete();
    }

    @Test
    void helloWorldApproach3() {
        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class).consumeWith(response -> {
                    var res = response.getResponseBody();
                    assert (res.equals("Hello World"));
                });
    }

    @Test
    void streamTestApproach1() {
        var stream = webTestClient.get().uri("/stream")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(Long.class).getResponseBody();

        StepVerifier.create(stream).expectNext(0L,1L).thenCancel().verify();
    }
}