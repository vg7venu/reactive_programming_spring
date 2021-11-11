package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringJoiner;

public class FluxAndMonoGeneratorService {
    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("venue","seven","even"))
                .log();
    }
    public Flux<String> namesFlux_flatmap(int lenOfString) {
        return Flux.fromIterable(List.of("lol","please","rolf")).map(String::toUpperCase)
                .filter(s-> s.length()<= lenOfString).map(name -> name+"l")
                .map(name -> name.toLowerCase())
                .flatMap(name -> stringSplitter(name));
    }

    public Flux<String> flapMapAsync(int lenOfString){
        return Flux.fromIterable(List.of("lol","please","rolf")).map(String::toUpperCase)
                .filter(s-> s.length()<= lenOfString).map(name -> name+"l")
                .map(name -> name.toLowerCase())
                .flatMap(name -> stringSplitterWithDelay(name).log());
    }

    public Flux<String> stringSplitterWithDelay(String name){
        var arrayOfStrings = name.split("");
        var delay = new Random().nextInt(2000);
        return Flux.fromArray(arrayOfStrings).delayElements(Duration.ofMillis(delay));
    }

    public Flux<String> stringSplitter(String name){
        var arrayOfStrings = name.split("");
        return Flux.fromArray(arrayOfStrings);
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

        fluxAndMonoGeneratorService.namesFlux_flatmap(5).subscribe(name-> System.out.println(name));
    }
}
