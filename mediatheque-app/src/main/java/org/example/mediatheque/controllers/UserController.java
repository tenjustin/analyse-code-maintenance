package org.example.mediatheque.controllers;

import org.example.mediatheque.database.EmpruntRepository;
import org.example.mediatheque.database.UserRepository;
import org.example.mediatheque.models.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@RestController
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmpruntRepository empruntRepository;

    Logger logger = Logger.getLogger(UserController.class.getName());

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Here you would typically save the user to a database
        // For this example, we will just return the user object
        if (user.uid == null) {
            user.uid = UUID.randomUUID();
        }
        logger.info(String.format("Creating user with uid : %s", user.uid));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/users")
    public User getUserById(@RequestParam String uid) {
        // Here you would typically retrieve the user from a database
        // For this example, we will just return a dummy user
        logger.info(String.format("Retrieving user with uid : %s", uid));
        return userRepository.findByUid(UUID.fromString(uid));
    }

    @PutMapping("/users")
    public User updateUser(@RequestParam String uuid, @RequestBody User user) {
        // Here you would typically update the user in a database
        // For this example, we will just return the updated user object
        if (user.uid == null) {
            user.uid = UUID.fromString(uuid);
        }
        logger.info(String.format("Updating user with uid : %s", user.uid));
        return userRepository.save(user);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@RequestParam String uuid) {
        // Here you would typically delete the user from a database
        // For this example, we will just return a 204 No Content response
        var userEmprunts = empruntRepository.findByUserUid(UUID.fromString(uuid));
        if (!userEmprunts.isEmpty()) {
            logger.warning(String.format("Cannot delete user with uid %s, they have active emprunts", uuid));
            return ResponseEntity.status(403).build(); // Forbidden
        }
        userRepository.deleteByUid(UUID.fromString(uuid));
        logger.info(String.format("Deleting user with uid : %s", uuid));
        return ResponseEntity.noContent().build();
    }
}