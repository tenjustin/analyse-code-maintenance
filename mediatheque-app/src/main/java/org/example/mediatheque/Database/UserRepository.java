package org.example.mediatheque.Database;

import org.example.mediatheque.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
    void deleteById(int id);
    User save(User user);
}
