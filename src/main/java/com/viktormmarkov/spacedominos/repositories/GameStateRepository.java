package com.viktormmarkov.spacedominos.repositories;

import com.viktormmarkov.spacedominos.models.game.GameState;
import com.viktormmarkov.spacedominos.models.game.Player;
import com.viktormmarkov.spacedominos.models.lobby.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public final class GameStateRepository {
    GameStateRepository() {
        // Private constructor to prevent instantiation
    }
    private static final ConcurrentHashMap<String, GameState> gameStates = new ConcurrentHashMap<>();

    public static GameState getGameState(String gameId) {
        return gameStates.get(gameId);
    }

    public static GameState initGameState(String gameId) {
        Player[] players = new Player[]{
                new Player("1", "Player 1"),
                new Player("2", "Player 2"),
                new Player("3", "Player 3"),
                new Player("4", "Player 4")
        };
        return gameStates.putIfAbsent(gameId, new GameState(gameId, players));
    }

    public static GameState initGameStateFromRoom(Room room) {
        if (room == null || room.getPlayers() == null || room.getPlayers().length == 0) {
            throw new IllegalArgumentException("Room or players cannot be null or empty");
        }
        Player[] players = new Player[room.getPlayers().length];
        for (int i = 0; i < room.getPlayers().length; i++) {
            players[i] = new Player(String.valueOf(room.getPlayers()[i]), "Player " + (i + 1));
        }
        return gameStates.putIfAbsent(room.getId(), new GameState(room.getId(), players));
    }

    public static List<String> getAllGameIds() {
        return gameStates.keySet().stream()
                .sorted()
                .toList();
    }
}
