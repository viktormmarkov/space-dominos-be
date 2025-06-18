package com.viktormmarkov.spacedominos.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    static Player player1;
    static Player player2;
    static Player player3;
    @BeforeAll
    static void setup() {
        player1 = new Player("player1", "Player 1");
        player2 = new Player("player2", "Player 2");
        player3 = new Player("player3", "Player 3");
    }

    @Test
    void initGameStateTest() {
        GameState gameStateWithFourPlayers = new GameState(
                "gameId",
                new Player[]{ player1, player2, player3, new Player("player4", "Player 4") }
        );

        GameState gameStateWithThreePlayers = new GameState(
                "gameId",
                new Player[]{ player1, player2, player3 }
        );

        GameState gameStateWithTwoPlayers = new GameState(
                "gameId",
                new Player[]{ player1, player2 }
        );

        // for two players, each player will play twice.
        assertEquals(2, gameStateWithTwoPlayers.getPlayers().length);
        assertEquals(4, gameStateWithTwoPlayers.getDraftTilesCount());
        assertEquals(4, gameStateWithTwoPlayers.getPlayerOrder().length);
        assertEquals(2, gameStateWithTwoPlayers.getPlayerBoards().keySet().size());

        assertEquals(3, gameStateWithThreePlayers.getPlayers().length);
        assertEquals(3, gameStateWithThreePlayers.getDraftTilesCount());
        assertEquals(3, gameStateWithThreePlayers.getPlayerOrder().length);
        assertEquals(3, gameStateWithThreePlayers.getPlayerBoards().keySet().size());

        assertEquals(4, gameStateWithFourPlayers.getPlayers().length);
        assertEquals(4, gameStateWithFourPlayers.getDraftTilesCount());
        assertEquals(4, gameStateWithFourPlayers.getPlayerBoards().keySet().size());
    }

    @Test
    void getNewDraftTiles() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                }
        );
        int originalTileCount = gameState.getTilesDeck().length;
        // Act

        gameState.getNewDraftTiles();

        // Assert
        assertEquals(gameState.getDraftTilesCount(), gameState.getDraftTiles().length);
        assertEquals(originalTileCount - gameState.getDraftTiles().length, gameState.getTilesDeck().length);
        System.out.println("Draft Tiles: " + gameState.getDraftTiles().length);
    }

    @Test
    void pickDraftTile_success() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                }
        );

        // Act
        gameState.pickDraftTile(0, "player1");

        // Assert
        assertEquals(gameState.getPlayerMap().get("player1").getNextTileChoice().draftIndex, 0);
    }

    @Test
    void pickDraftTile_fail_playerAlreadyMoved() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                }
        );

        // Act
        gameState.pickDraftTile(0, "player1");

        assertThrows(IllegalStateException.class, () -> {
            gameState.pickDraftTile(1, "player1");
        });
    }

    @Test
    void pickDraftTile_fail_tileAlreadyPicked() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                }
        );

        // Act
        gameState.pickDraftTile(0, "player1");

        // Assert
        assertThrows(IllegalStateException.class, () -> {
            gameState.pickDraftTile(0, "player2");
        });
    }

   @Test
   void playTile_success() {
   }

   void playTile_fail() {

   }
}