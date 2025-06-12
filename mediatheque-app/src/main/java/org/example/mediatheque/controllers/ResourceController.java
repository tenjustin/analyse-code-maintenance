package org.example.mediatheque.controllers;

import org.example.mediatheque.database.ResourceRepository;
import org.example.mediatheque.models.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
public class ResourceController {
    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping("/resources/getAll")
    public List<Resource> getAllResources() {
        // This method retrieves all resources from the database
        return resourceRepository.findAll();
    }

    @PostMapping("/resources")
    public Resource createResource(@RequestBody Resource resource) {
        // This method saves a new resource to the database
        if (resource.uid == null) {
            resource.uid = UUID.randomUUID();
        }
        return resourceRepository.save(resource);
    }

    @PutMapping("/resources")
    public Resource updateResource(@RequestParam String uid, @RequestBody Resource resource) {
        // This method updates an existing resource in the database
        if (resource.uid == null) {
            resource.uid = UUID.fromString(uid);
        }
        return resourceRepository.save(resource);
    }

    @DeleteMapping("/resources")
    public void deleteResource(@RequestParam String uid) {
        // This method deletes a resource from the database by its ID
        resourceRepository.deleteById(UUID.fromString(uid));
    }

    @GetMapping("/resources")
    public Resource getResourceById(@RequestParam String uid) {
        // This method retrieves a resource by its ID
        return resourceRepository.findById(UUID.fromString(uid)).orElse(null);
    }
}
