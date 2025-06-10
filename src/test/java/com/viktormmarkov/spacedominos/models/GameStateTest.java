package com.viktormmarkov.spacedominos.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void getNewDraftTiles() {
    }

    @Test
    void pickDraftOption_success() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                },
                null,
                3
        );

        // Act
        gameState.pickDraftOption(0, "player1");

        // Assert
        assertTrue(gameState.getPlayerDraftPicks().containsKey("player1"));
    }

    @Test
    void pickDraftOption_fail_playerAlreadyMoved() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                },
                null,
                3
        );

        // Act
        gameState.pickDraftOption(0, "player1");

        assertThrows(IllegalStateException.class, () -> {
            gameState.pickDraftOption(1, "player1");
        });
    }

    @Test
    void pickDraftOption_fail_tileAlreadyPicked() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                },
                null,
                3
        );

        // Act
        gameState.pickDraftOption(0, "player1");

        // Assert
        assertThrows(IllegalStateException.class, () -> {
            gameState.pickDraftOption(0, "player2");
        });
    }

    @Test
    void getCurrentPlayerId() {
    }

    @Test
    void getPlayerOrder() {
    }
}