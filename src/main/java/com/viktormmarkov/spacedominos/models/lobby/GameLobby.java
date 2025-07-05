package com.viktormmarkov.spacedominos.models.lobby;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "game_lobby")
public class GameLobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date endedAt;

    @Column(name = "host_id")
    private int host;

    public GameLobby() {
        this.createdAt = new Date();
    }

    public GameLobby(String name, String password) {
        this.name = name;
        this.password = password;
        this.createdAt = new Date();
    }
}
