package org.example.webfluxplayground.controller.v2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v2/mvc")
public class SpringMvcV2Controller {
    private final List<String> users = new ArrayList<>();

    @GetMapping
    public ResponseEntity<String> getAllUsers() {
        return new ResponseEntity<>("All users retrieved: " + users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String user) {
        users.add(user);
        return new ResponseEntity<>("User created: " + user, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody String user) {
        if (users.contains(user)) {
            return new ResponseEntity<>("User updated: " + user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody String user) {
        if (users.remove(user)) {
            return new ResponseEntity<>("User deleted: " + user, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
