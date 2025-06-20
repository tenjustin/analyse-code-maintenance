package org.example.mediatheque.controllers;

import org.example.mediatheque.database.EmpruntRepository;
import org.example.mediatheque.database.ResourceRepository;
import org.example.mediatheque.database.UserRepository;
import org.example.mediatheque.models.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class EmpruntController {
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;

    Logger logger = Logger.getLogger(EmpruntController.class.getName());

    @GetMapping("/emprunts/getAll")
    public List<Emprunt> getEmprunts() {
        // This method retrieves all emprunts from the database
        logger.info("Retrieving all emprunts");
        return empruntRepository.findAll(); // Placeholder for actual implementation
    }

    @GetMapping("/emprunts")
    public Emprunt getEmpruntByUid(@RequestParam String uid) {
        // This method retrieves an emprunt by its UID
        logger.info(String.format("Retrieving emprunts by uid %s", uid));
        return empruntRepository.findByUid(UUID.fromString(uid));
    }

    @PostMapping("/emprunts")
    public Emprunt createEmprunt(@RequestParam String userEmail, @RequestParam String resourceName) {
        // This method creates a new emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.user = userRepository.findByEmail(userEmail);
        emprunt.resource = resourceRepository.findByTitle(resourceName);
        emprunt.startDate = LocalDate.now(); // Assuming startDate is the current date
        emprunt.endDate = emprunt.startDate.plusWeeks(1);
        emprunt.resource.isAvailable = false;
        resourceRepository.save(emprunt.resource);
        logger.info(String.format("Creating emprunt for user: %s with resource: %s", userEmail, resourceName));
        return empruntRepository.save(emprunt);
    }

    @PutMapping("/emprunts")
    public Emprunt updateEmprunt(@RequestParam String userEmail, @RequestParam String resourceName) {
        // This method updates an existing emprunt
        Emprunt emprunt = empruntRepository.findByUserAndResource(userRepository.findByEmail(userEmail), resourceRepository.findByTitle(resourceName));
        emprunt.endDate = LocalDate.now();
        emprunt.resource.isAvailable = true; // Mark the resource as available
        resourceRepository.save(emprunt.resource);
        logger.info(String.format("Updating emprunt for user: %s with resource: %s", userEmail, resourceName));
        return empruntRepository.save(emprunt);
    }

    @DeleteMapping("/emprunts")
    public ResponseEntity<Object> deleteEmprunt(@RequestParam String uid) {
        // This method deletes an emprunt by its UID
        Emprunt emprunt = empruntRepository.findByUid(UUID.fromString(uid));
        if (emprunt != null) {
            emprunt.resource.isAvailable = true; // Mark the resource as available
            resourceRepository.save(emprunt.resource);
            empruntRepository.deleteByUid(UUID.fromString(uid));
        }
        logger.info(String.format("Deleting emprunt for user: %s", uid));
        return ResponseEntity.noContent().build();
    }
}
