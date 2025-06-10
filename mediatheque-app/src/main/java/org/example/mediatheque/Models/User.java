package org.example.mediatheque.Models;

@Entity
public record User(String id, String username, String email, String password) {}