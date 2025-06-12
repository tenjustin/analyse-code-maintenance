package org.example.mediatheque.database;

import org.example.mediatheque.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository extends JpaRepository<Resource, UUID> {
    public Resource save(Resource resource);
    public Resource findByUid(UUID uid);
    public Resource findByTitle(String title);
    public List<Resource> findAll();
}
