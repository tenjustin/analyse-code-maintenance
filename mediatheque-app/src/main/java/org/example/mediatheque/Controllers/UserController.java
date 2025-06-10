package org.example.mediatheque.Controllers;

import org.example.mediatheque.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController{
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Here you would typically save the user to a database
        // For this example, we will just return the user object
        return user;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        // Here you would typically retrieve the user from a database
        // For this example, we will just return a dummy user
        return new User(id, "dummyUser", "tt@tt.fr", "password123");
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        // Here you would typically update the user in a database
        // For this example, we will just return the updated user object
        return new User(id, user.username(), user.email(), user.password());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        // Here you would typically delete the user from a database
        // For this example, we will just return a 204 No Content response
        return ResponseEntity.noContent().build();
    }
}