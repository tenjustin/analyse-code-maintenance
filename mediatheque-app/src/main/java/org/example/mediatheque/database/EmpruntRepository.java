package org.example.mediatheque.database;

import org.example.mediatheque.models.Emprunt;
import org.example.mediatheque.models.Resource;
import org.example.mediatheque.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmpruntRepository extends JpaRepository<Emprunt, UUID> {
    public Emprunt save(Emprunt emprunt);
    public Emprunt findByUid(UUID uid);
    public void deleteByUid(UUID uid);
    public Emprunt findByUserAndResource(User user, Resource resource);
    public Emprunt findByResourceUid(UUID resourceUid);
    public List<Emprunt> findByUserUid(UUID userUid);
    public List<Emprunt> findAll();
}
