package org.example.mediatheque.database;

import org.example.mediatheque.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByUid(UUID uid);
    void deleteByUid(UUID uid);
    User save(User user);
}
