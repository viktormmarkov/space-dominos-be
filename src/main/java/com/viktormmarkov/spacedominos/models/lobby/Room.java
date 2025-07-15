package com.viktormmarkov.spacedominos.models.lobby;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("room")
public class Room implements Serializable {
    private String id;
    private String name;
    private String password;
    private int hostId;
    private int[] players;

    public Room(String id, String name, String password, int hostId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.hostId = hostId;
        this.players = new int[0]; // Initialize with an empty player list
        this.players = joinPlayer(hostId); // Add host as the first player
    }

    public int[] joinPlayer(int playerId) {
        if (players == null) {
            players = new int[1];
            players[0] = playerId;
        } else {
            int[] newPlayers = new int[players.length + 1];
            System.arraycopy(players, 0, newPlayers, 0, players.length);
            newPlayers[players.length] = playerId;
            players = newPlayers;
        }
        return players;
    }

    public int[] leavePlayer(int playerId) {
        if (players == null || players.length == 0) {
            return new int[0]; // No players to remove
        }

        int[] newPlayers = Arrays.stream(players).filter(p -> p != playerId).toArray();
        players = newPlayers;
        return players;
    }

}
