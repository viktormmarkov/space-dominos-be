package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enitities.Position;
import com.viktormmarkov.spacedominos.domain.enums.GamePhaseEnum;
import com.viktormmarkov.spacedominos.domain.enums.TileType;
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
                new Player[]{player1, player2, player3, new Player("player4", "Player 4")}
        );

        GameState gameStateWithThreePlayers = new GameState(
                "gameId",
                new Player[]{player1, player2, player3}
        );

        GameState gameStateWithTwoPlayers = new GameState(
                "gameId",
                new Player[]{player1, player2}
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
    void chooseTile_success() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                }
        );

        // Act
        gameState.chooseTile("player1", 0);

        // Assert
        assertEquals(gameState.getPlayerMap().get("player1").getNextTileChoice().draftIndex, 0);
    }

    @Test
    void chooseTile_fail_playerAlreadyMoved() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                }
        );

        // Act
        gameState.chooseTile("player1", 0);

        assertThrows(IllegalStateException.class, () -> {
            gameState.chooseTile("player1", 1);
        });
    }

    @Test
    void chooseTile_fail_tileAlreadyPicked() {
        // Arrange
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                }
        );

        // Act
        gameState.chooseTile( "player1", 0);

        // Assert
        assertThrows(IllegalStateException.class, () -> {
            gameState.chooseTile("player2", 0);
        });
    }

    @Test
    void playTile_success() {
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2"),
                        new Player("player3", "Player 3")
                }
        );
        String lastPlayer = gameState.getPlayerOrder()[2];
        gameState.setGamePhase(GamePhaseEnum.PLACE_TILES);
        SquareTile squareTile1 = new SquareTile(TileType.GRASS, 1);
        SquareTile squareTile2 = new SquareTile(TileType.GRASS, 2);
        gameState.playTile(
                lastPlayer,
                new Position(2, 1),
                new Position(3, 1),
                new Tile(4, new SquareTile[]{squareTile1, squareTile2}));


        assertEquals(gameState.getGamePhase(), GamePhaseEnum.CHOOSE_TILES);
    }

    @Test
    void playTile_fail() {
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2"),
                        new Player("player3", "Player 3")
                }
        );
        String lastPlayer = gameState.getPlayerOrder()[2];
        gameState.setGamePhase(GamePhaseEnum.PLACE_TILES);
        SquareTile squareTile1 = new SquareTile(TileType.GRASS, 1);
        SquareTile squareTile2 = new SquareTile(TileType.GRASS, 2);

        assertThrows(IllegalStateException.class, () -> {
            gameState.playTile(
                    lastPlayer,
                    new Position(0, 0),
                    new Position(1, 0),
                    new Tile(4, new SquareTile[]{squareTile1, squareTile2}));
        });
        assertEquals(gameState.getGamePhase(), GamePhaseEnum.PLACE_TILES);
    }

    @Test
    void nextPhase_chooseTileFirstRound() {
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2"),
                        new Player("player3", "Player 3")
                }
        );
        SquareTile squareTile1 = new SquareTile(TileType.GRASS, 1);
        SquareTile squareTile2 = new SquareTile(TileType.GRASS, 2);
        Tile dummyTile = new Tile(4, new SquareTile[]{squareTile1, squareTile2});

        gameState.getPlayerById("player1").setNextTileChoice(dummyTile, 0);
        gameState.getPlayerById("player2").setNextTileChoice(dummyTile, 1);
        gameState.getPlayerById("player3").setNextTileChoice(dummyTile, 2);
        gameState.setCurrentPlayerId(gameState.getPlayerOrder()[2]);
        gameState.setCurrentPlayerIndex(2);

        gameState.nextPhase();

        assertEquals(GamePhaseEnum.CHOOSE_TILES, gameState.getGamePhase());
    }

    @Test
    void nextPhase_chooseTileEndRound() {
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2"),
                        new Player("player3", "Player 3")
                }
        );
        int lastPlayerIndex = gameState.getPlayerOrder().length - 1;
        SquareTile squareTile1 = new SquareTile(TileType.GRASS, 1);
        SquareTile squareTile2 = new SquareTile(TileType.GRASS, 2);
        gameState.setTurnCounter(1);
        Tile dummyTile = new Tile(4, new SquareTile[]{squareTile1, squareTile2});

        gameState.getPlayerById("player1").setNextTileChoice(dummyTile, 0);
        gameState.getPlayerById("player2").setNextTileChoice(dummyTile, 1);
        gameState.getPlayerById("player3").setNextTileChoice(dummyTile, 2);
        gameState.setCurrentPlayerId(gameState.getPlayerOrder()[lastPlayerIndex]);
        gameState.setCurrentPlayerIndex(2);

        gameState.nextPhase();

        assertEquals(GamePhaseEnum.PLACE_TILES, gameState.getGamePhase());
        assertEquals(gameState.getCurrentPlayerIndex(), lastPlayerIndex);
    }

    @Test
    void nextPhase_placeTile() {
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2"),
                        new Player("player3", "Player 3")
                }
        );
        gameState.setGamePhase(GamePhaseEnum.PLACE_TILES);
        SquareTile squareTile1 = new SquareTile(TileType.GRASS, 1);
        SquareTile squareTile2 = new SquareTile(TileType.GRASS, 2);
        Tile dummyTile = new Tile(4, new SquareTile[]{squareTile1, squareTile2});

        gameState.getPlayerById("player1").setNextTileChoice(dummyTile, 0);
        gameState.getPlayerById("player2").setNextTileChoice(dummyTile, 1);
        gameState.getPlayerById("player3").setNextTileChoice(dummyTile, 2);
        gameState.setCurrentPlayerId(gameState.getPlayerOrder()[2]);
        gameState.setCurrentPlayerIndex(2);

        gameState.nextPhase();

        assertEquals(GamePhaseEnum.CHOOSE_TILES, gameState.getGamePhase());
        assertEquals(gameState.getCurrentPlayerIndex(), 0);
    }
}