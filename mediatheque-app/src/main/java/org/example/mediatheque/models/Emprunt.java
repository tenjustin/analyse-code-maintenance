package org.example.mediatheque.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public UUID uid;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    public Resource resource;
    public LocalDate startDate;
    public LocalDate endDate;

    public Emprunt() {
    }

    public Emprunt(int id, User user, Resource resource, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.uid = UUID.randomUUID();
        this.user = user;
        this.resource = resource;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
