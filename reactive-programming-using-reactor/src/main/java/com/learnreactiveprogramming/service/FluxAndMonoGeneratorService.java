package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

public class FluxAndMonoGeneratorService {
    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("venue","seven","even"))
                .log();
    }
    public Flux<String> namesFlux_map(int lenOfString) {
        return Flux.fromIterable(List.of("captains","displease","marshmallows")).map(String::toUpperCase)
                .filter(s-> s.length()>= lenOfString).map(name -> name+" masti")
                .map(name -> name.toLowerCase());
    }

    public Flux<String> namesFlux_immutable() {
        var namesImmutable =  Flux.fromIterable(List.of("LOL","ROLF"));
        return namesImmutable;
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

        fluxAndMonoGeneratorService.namesFlux_map(9).subscribe(name-> System.out.println(name));
    }
}
