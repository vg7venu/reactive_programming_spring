package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {
    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("venue","seven","even")).log();
    }

    public Mono<String> namesMono() {
        return Mono.just("alex").log();
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        System.out.println(fluxAndMonoGeneratorService.namesFlux().subscribe(name -> {
            System.out.println("I'm "+name);
        }));

        fluxAndMonoGeneratorService.namesMono().subscribe(name -> {
            System.out.println("This is mono "+name);
        });
    }
}
