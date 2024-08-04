package org.example.webfluxplayground.controller.v2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/webflux")
public class SpringWebFluxV2Controller {
    private final List<String> users = new ArrayList<>();

    @GetMapping
    public Mono<List<String>> getAllUsers() {
        return Mono.just(users);
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createUser(@RequestBody Mono<String> userMono) {
        return userMono.flatMap(user -> {
            users.add(user);
            return Mono.just(new ResponseEntity<>("User created: " + user, HttpStatus.CREATED));
        });
    }

    @PutMapping
    public Mono<ResponseEntity<String>> updateUser(@RequestBody Mono<String> userMono) {
        return userMono.flatMap(user -> {
            if (users.contains(user)) {
                return Mono.just(new ResponseEntity<>("User updated: " + user, HttpStatus.OK));
            } else {
                return Mono.just(new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND));
            }
        });
    }

    @DeleteMapping
    public Mono<ResponseEntity<String>> deleteUser(@RequestBody Mono<String> userMono) {
        return userMono.flatMap(user -> {
            if (users.remove(user)) {
                return Mono.just(new ResponseEntity<>("User deleted: " + user, HttpStatus.NO_CONTENT));
            } else {
                return Mono.just(new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND));
            }
        });
    }
}
