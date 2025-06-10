package com.viktormmarkov.spacedominos.repositories;

import com.viktormmarkov.spacedominos.models.GameState;

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
        return gameStates.putIfAbsent(gameId, new GameState(gameId, null, null, 0));
    }

    public static List<String> getAllGameIds() {
        return gameStates.keySet().stream()
                .sorted()
                .toList();
    }
}
