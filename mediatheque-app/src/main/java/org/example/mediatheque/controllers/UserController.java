package org.example.mediatheque.controllers;

import org.example.mediatheque.database.UserRepository;
import org.example.mediatheque.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController{
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Here you would typically save the user to a database
        // For this example, we will just return the user object
        if (user.uid == null) {
            user.uid = UUID.randomUUID();
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/users")
    public User getUserById(@RequestParam String uid) {
        // Here you would typically retrieve the user from a database
        // For this example, we will just return a dummy user
        return userRepository.findByUid(UUID.fromString(uid));
    }

    @PutMapping("/users")
    public User updateUser(@RequestParam String uuid, @RequestBody User user) {
        // Here you would typically update the user in a database
        // For this example, we will just return the updated user object
        if (user.uid == null) {
            user.uid = UUID.fromString(uuid);
        }
        return userRepository.save(user);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@RequestParam String uuid) {
        // Here you would typically delete the user from a database
        // For this example, we will just return a 204 No Content response
        userRepository.deleteByUid(UUID.fromString(uuid));
        return ResponseEntity.noContent().build();
    }
}