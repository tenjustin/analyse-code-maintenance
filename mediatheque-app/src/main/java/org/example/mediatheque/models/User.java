package org.example.mediatheque.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public UUID uid;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String nationality;

    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String password, String nationality) {
        this.id = id;
        this.uid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.nationality = nationality;
    }
}