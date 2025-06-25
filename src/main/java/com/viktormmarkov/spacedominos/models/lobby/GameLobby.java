package com.viktormmarkov.spacedominos.models.lobby;

import com.viktormmarkov.spacedominos.models.lobby.User;
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
    private User host;
    private User[] players;
}
