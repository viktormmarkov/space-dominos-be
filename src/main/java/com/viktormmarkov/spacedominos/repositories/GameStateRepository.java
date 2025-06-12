package com.viktormmarkov.spacedominos.repositories;

import com.viktormmarkov.spacedominos.models.GameState;
import com.viktormmarkov.spacedominos.models.Player;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

    public static List<String> getAllGameIds() {
        return gameStates.keySet().stream()
                .sorted()
                .toList();
    }
}
