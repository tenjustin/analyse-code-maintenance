package org.example.mediatheque.Database;

import org.example.mediatheque.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<User, Integer> {

}