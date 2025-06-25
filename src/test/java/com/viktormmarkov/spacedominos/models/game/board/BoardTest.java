package com.viktormmarkov.spacedominos.models.game.board;

import com.viktormmarkov.spacedominos.domain.enums.TileType;
import com.viktormmarkov.spacedominos.helpers.BoardDrawer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;
    Position center;
    static Tile tile1;
    static Tile tile2;
    static SquareTile squareTile1;
    static SquareTile squareTile2;
    static SquareTile squareTile3;
    static SquareTile squareTile4;

    @BeforeAll
    static void init() {
        squareTile1 = new SquareTile(TileType.GRASS, 0);
        squareTile2 = new SquareTile(TileType.WATER, 1);
        squareTile3 = new SquareTile(TileType.GRASS, 2);
        squareTile4 = new SquareTile(TileType.WATER, 3);
        tile1 = new Tile(0, new SquareTile[] {squareTile1, squareTile2});
        tile2 = new Tile(1, new SquareTile[] {squareTile3, squareTile4});
    }

    @BeforeEach
    void setUp() {
        board = new Board(5, 5);
        center = board.getCenterTile();
    }

    @Test
    void initPlayerCastle_shouldReturnCenterPosition() {
        // Check if the center position is correctly initialized
        assertEquals(new Position(2, 2), center);
        // Check if the castle tile is placed at the center position
        assertNotNull(board.getTileAt(center));
        assertEquals(TileType.CASTLE, board.getTileAt(center).getType());
    }

    @Test
    void placeGameTile_shouldWorkOnAdjacentPositionsToCastle() {
        Position position1 = new Position(center.x() , center.y() + 1);
        Position position2 = new Position(center.x() + 1, center.y() + 1);

        board.placeGameTile(position1, position2, tile1);
        BoardDrawer.drawBoard(board);
        // Check if the tiles are placed correctly
        assertEquals(squareTile1, board.getTileAt(position1));
        assertEquals(squareTile2, board.getTileAt(position2));

    }

    @Test
    void placeGameTile_shouldWorkOnAdjacentPositionsToFirstTile() {
        Position position1 = new Position(center.x() , center.y() + 1);
        Position position2 = new Position(center.x() + 1, center.y() + 1);
        Position position3 = new Position(center.x(), center.y() + 2);
        Position position4 = new Position(center.x() + 1, center.y() + 2);

        board.placeGameTile(position1, position2, tile1);
        board.placeGameTile(position3, position4, tile2);

        BoardDrawer.drawBoard(board);
        // Check if the tiles are placed correctly
        assertEquals(squareTile3, board.getTileAt(position3));
        assertEquals(squareTile4, board.getTileAt(position4));
    }

    @Test
    void placeGameTile_shouldNotWorkOnNonAdjacentPositions() {
        Position position1 = new Position(center.x() , center.y() + 1);
        Position position2 = new Position(center.x() + 1, center.y() + 1);
        Position position3 = new Position(0, 0);
        Position position4 = new Position(0, 1);


        board.placeGameTile(position1, position2, tile1);

        BoardDrawer.drawBoardMove(board, position3, position4, tile2);
        assertThrows(IllegalArgumentException.class, () -> {
            board.placeGameTile(position3, position4, tile2);
        });
    }
}