package com.viktormmarkov.spacedominos.models.lobby;

import com.viktormmarkov.spacedominos.models.lobby.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class GameLobby {
    private String id;
    private String name;
    private String password;
    private Date createdAt;
    private Date startedAt;
    private Date endedAt;
    private User host;
    private User[] players;

    private String generateId() {
        return "lobby-" + System.currentTimeMillis();
    }

    public GameLobby(String name, String password) {
        this.id = generateId();
        this.name = name;
        this.password = password;
        this.createdAt = new Date();
    }
}
