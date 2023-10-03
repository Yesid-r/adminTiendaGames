package com.example.admin.entities;


import com.example.admin.enums.Clasification;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private String description;
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Clasification clasification;
    private int storage;
    private int memory;



    @ManyToMany(mappedBy = "games", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Client> clients;

    public Game(Long id, String name, BigDecimal price, String description, LocalDate releaseDate, Clasification clasification, int storage, int memory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
        this.clasification = clasification;
        this.storage = storage;
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", clasification=" + clasification +
                ", storage=" + storage +
                ", memory=" + memory +
                ", clients=" + clients +
                '}';
    }
}
