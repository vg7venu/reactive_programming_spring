package learn.reactive.react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import learn.reactive.react.service.AlfredService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BaseController {
    @Autowired
    private AlfredService alfredService;

    @GetMapping("/hello")
    public Mono<String> helloWorld() {
        return alfredService.helloWorld();
    }

    @GetMapping("/defaultIf")
    public Flux<String> defaultIf() {
        return alfredService.defaultIfEmpty();
    }

    @GetMapping("/switchIf/{number}")
    public Flux<String> switchIf(@RequestParam Integer num) {
        return alfredService.switchIfEmpty(num);
    }

    @GetMapping(value = "/concat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> concat() {
        return alfredService.concat();
    }

    @GetMapping("/concatWith")
    public Flux<String> concatWith() {
        return alfredService.concatWith();
    }

    @GetMapping("/merge")
    public Flux<String> merge() {
        return alfredService.merge();
    }

    @GetMapping("/mergeWith")
    public Flux<String> mergeWith() {
        return alfredService.mergeWith();
    }
}
