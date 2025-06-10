package org.example.mediatheque.Controllers;

import org.example.mediatheque.Database.UserRepository;
import org.example.mediatheque.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController{

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Here you would typically save the user to a database
        // For this example, we will just return the user object
        userRepository.save(user);
        return user;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        // Here you would typically retrieve the user from a database
        // For this example, we will just return a dummy user
        return userRepository.findById(Integer.parseInt(id));
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        // Here you would typically update the user in a database
        // For this example, we will just return the updated user object
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        // Here you would typically delete the user from a database
        // For this example, we will just return a 204 No Content response
        userRepository.deleteById(Integer.parseInt(id));
        return ResponseEntity.noContent().build();
    }
}