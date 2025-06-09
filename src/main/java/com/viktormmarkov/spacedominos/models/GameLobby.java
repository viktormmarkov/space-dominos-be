package com.viktormmarkov.spacedominos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class GameLobby {
    private String id;
    private String name;
    private String password;
    private Date createdAt;
    private Date startedAt;
    private Date endedAt;
    private Player host;
    private Player[] players;
}
