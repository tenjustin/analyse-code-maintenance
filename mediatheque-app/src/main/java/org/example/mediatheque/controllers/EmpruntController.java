package org.example.mediatheque.controllers;

import org.example.mediatheque.database.EmpruntRepository;
import org.example.mediatheque.database.ResourceRepository;
import org.example.mediatheque.database.UserRepository;
import org.example.mediatheque.models.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class EmpruntController {
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/emprunts/getAll")
    public List<Emprunt> getEmprunts() {
        // This method retrieves all emprunts from the database
        return empruntRepository.findAll(); // Placeholder for actual implementation
    }

    @GetMapping("/emprunts")
    public Emprunt getEmpruntByUid(@RequestParam String uid) {
        // This method retrieves an emprunt by its UID
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
        return empruntRepository.save(emprunt);
    }

    @PutMapping("/emprunts")
    public Emprunt updateEmprunt(@RequestParam String userEmail, @RequestParam String resourceName) {
        // This method updates an existing emprunt
        Emprunt emprunt = empruntRepository.findByUserAndResource(userRepository.findByEmail(userEmail), resourceRepository.findByTitle(resourceName));
        emprunt.endDate = LocalDate.now();
        emprunt.resource.isAvailable = true; // Mark the resource as available
        resourceRepository.save(emprunt.resource);
        return empruntRepository.save(emprunt);
    }

    @DeleteMapping("/emprunts")
    public void deleteEmprunt(@RequestParam String uid) {
        // This method deletes an emprunt by its UID
        Emprunt emprunt = empruntRepository.findByUid(UUID.fromString(uid));
        if (emprunt != null) {
            emprunt.resource.isAvailable = true; // Mark the resource as available
            resourceRepository.save(emprunt.resource);
            empruntRepository.deleteByUid(UUID.fromString(uid));
        }
    }
}
