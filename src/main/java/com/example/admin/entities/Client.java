package com.example.admin.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String name;
    private String lastName;
    private String email;
    @Column(unique = true)
    private String nickName;
    private String password;
    private String rol;


    @ManyToMany
    @JoinTable(name = "client_games", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> games;

    public Client(Long clientId, String name, String lastName, String email, String nickName, String password) {
        this.clientId = clientId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

    public Client() {

    }

}
