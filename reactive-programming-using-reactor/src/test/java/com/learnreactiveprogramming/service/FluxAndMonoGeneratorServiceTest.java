package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService =
            new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();
        StepVerifier.create(namesFlux) //the createfuntion call would do the subscribe() call to trigger publisher to give items
//                .expectNext("venue","seven","even")
                .expectNext("venue")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void testNamesFlux() {
        var namesCapitalFlux = fluxAndMonoGeneratorService.namesFlux();
        StepVerifier.create(namesCapitalFlux)
                .expectNext("VENUE","SEVEN","EVEN")
                .verifyComplete();
    }

    @Test
    void namesFlux_map() {
        int lenOfString = 9;
        var namesFlux_map = fluxAndMonoGeneratorService.namesFlux_map(lenOfString);
        StepVerifier.create(namesFlux_map).expectNext("displease masti","marshmallows masti")
                .verifyComplete();
    }

    @Test
    void namesFlux_immutable() {
        var names_immutable = fluxAndMonoGeneratorService.namesFlux_immutable();
        StepVerifier.create(names_immutable)
                .expectNext("LOL","ROLF")
                .verifyComplete();
    }
}