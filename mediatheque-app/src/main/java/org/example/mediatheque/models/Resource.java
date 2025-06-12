package org.example.mediatheque.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public UUID uid;
    public  String title;
    public String author;
    public ResourcesType type;
    public boolean isAvailable;

    public Resource() {
    }

    public Resource(int id, String title, String author, ResourcesType type, boolean isAvailable) {
        this.id = id;
        this.uid = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.type = type;
        this.isAvailable = isAvailable;
    }
}
