package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enitities.Position;
import com.viktormmarkov.spacedominos.domain.enums.TileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.viktormmarkov.spacedominos.models.Board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;
    Position center;
    static GameTile gameTile1;
    static GameTile gameTile2;
    static Tile tile1;
    static Tile tile2;
    static Tile tile3;
    static Tile tile4;

    @BeforeAll
    static void init() {
        tile1 = new Tile(TileType.GRASS, 0);
        tile2 = new Tile(TileType.WATER, 1);
        tile3 = new Tile(TileType.GRASS, 2);
        tile4 = new Tile(TileType.WATER, 3);
        gameTile1 = new GameTile(0, new Tile[] {tile1, tile2});
        gameTile2 = new GameTile(1, new Tile[] {tile3, tile4});
        // This method is called once before all tests in this class
        // You can use it to set up any static resources if needed
    }

    @BeforeEach
    void setUp() {
        board = new Board(5, 5);
        center = board.initPlayerCastle();
    }

    @Test
    void placeGameTile_shouldWorkOnAdjacentPositionsToCastle() {
        Position position1 = new Position(center.x() , center.y() + 1);
        Position position2 = new Position(center.x() + 1, center.y() + 1);

        board.placeGameTile(position1, position2, gameTile1);
        board.drawBoard();
        // Check if the tiles are placed correctly
        assertEquals(tile1, board.getTileAt(position1));
        assertEquals(tile2, board.getTileAt(position2));

    }

    @Test
    void placeGameTile_shouldWorkOnAdjacentPositionsToFirstTile() {
        Position position1 = new Position(center.x() , center.y() + 1);
        Position position2 = new Position(center.x() + 1, center.y() + 1);
        Position position3 = new Position(center.x(), center.y() + 2);
        Position position4 = new Position(center.x() + 1, center.y() + 2);

        board.placeGameTile(position1, position2, gameTile1);
        board.placeGameTile(position3, position4, gameTile2);

        board.drawBoard();
        // Check if the tiles are placed correctly
        assertEquals(tile3, board.getTileAt(position3));
        assertEquals(tile4, board.getTileAt(position4));
    }

    @Test
    void placeGameTile_shouldNotWorkOnNonAdjacentPositions() {
        Position position1 = new Position(center.x() , center.y() + 1);
        Position position2 = new Position(center.x() + 1, center.y() + 1);
        Position position3 = new Position(0, 0);
        Position position4 = new Position(0, 1);


        board.placeGameTile(position1, position2, gameTile1);

        Board.drawBoardMove(board, position3, position4, gameTile2);
        assertThrows(IllegalArgumentException.class, () -> {
            board.placeGameTile(position3, position4, gameTile2);
        });

    }


    @Test
    void getWidth() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void getTiles() {
    }

    @Test
    void setWidth() {
    }

    @Test
    void setHeight() {
    }

    @Test
    void setTiles() {
    }
}