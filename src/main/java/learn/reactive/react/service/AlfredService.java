package learn.reactive.react.service;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlfredService {

    public Mono<String> helloWorld() {
        return Mono.just("Hello World");
    }

    public Flux<String> defaultIfEmpty() {
        return Flux.fromIterable(List.of("Hai", "hello", "how"))
                .filter(s -> s.length() < 3)
                .defaultIfEmpty("Default Value is invoked");
    }

    Function<Flux<String>, Flux<String>> fun = data -> data.map(datum -> datum.toUpperCase());

    public Flux<String> switchIfEmpty(Integer num) {
        Flux<String> defFlux = Flux.just("Default Value").transform(fun);
        return Flux.fromIterable(List.of("Hai", "hello", "how", "mountain", "more letter word"))
                .transform(fun)
                .filter(s -> s.length() > num)
                .switchIfEmpty(defFlux);
    }

    public Flux<String> concat() {
        return Flux.concat(Flux.just("String 1"), Flux.just("String 2"))
                .delayElements(Duration.ofMillis(1000));
    }

    public Flux<String> concatWith() {
        Flux<String> con = Flux.just("item4", "item5", "item6");
        return Mono.just("item1").concatWith(con);
    }

    public Flux<String> merge() {
        Flux<String> item = Flux.just("data 1", "data 2").delayElements(Duration.ofMillis(1000));
        Flux<String> item2 = Flux.just("data 3", "data 4").delayElements(Duration.ofMillis(1500));
        return Flux.merge(item, item2);
    }

    public Flux<String> mergeWith() {
        Flux<String> item = Flux.just("data 1", "data 2").delayElements(Duration.ofMillis(1000));
        Mono<String> item2 = Mono.just("data 3").delayElement(Duration.ofMillis(1500));
        return item.mergeWith(item2);
    }
}
