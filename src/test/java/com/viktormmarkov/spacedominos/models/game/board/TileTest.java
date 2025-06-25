package com.viktormmarkov.spacedominos.models.game.board;

import com.viktormmarkov.spacedominos.domain.enums.TileType;
import com.viktormmarkov.spacedominos.repositories.TilesRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    void isExistingTile() {
        Tile tile = TilesRepository.getTiles()[0];

        assertTrue(tile.isExistingTile());
    }
    

    @Test
    void isExistingTile_fail_outOfBounds() {
        Tile tile = new Tile(45, new SquareTile[]{new SquareTile(TileType.GRASS, 0), new SquareTile(TileType.WATER, 1)});

        assertFalse(tile.isExistingTile());
    }

    @Test
    void isExistingTile_fail_noMatchTileType() {
        Tile tile = TilesRepository.getTiles()[0].clone();
        tile.setSquareTiles(new SquareTile[]{new SquareTile(TileType.GRASS, 0), new SquareTile(TileType.GRASS, 1)});

        assertFalse(tile.isExistingTile());
    }

    @Test
    void isExistingTile_fail_noMatchCrownNumber() {
        Tile tile = TilesRepository.getTiles()[0].clone();
        tile.getSquareTiles()[0].setNumberOfCrowns(566);

        assertFalse(tile.isExistingTile());
    }


    @Test
    public void canInstantiateTile() {
        SquareTile squareTile = new SquareTile(TileType.GRASS, 0);
        Tile tile = new Tile(1, new SquareTile[]{squareTile, squareTile});
        assertNotNull(tile);
    }

    @Test
    public void testEquals() {
        SquareTile squareTile1 = new SquareTile(TileType.GRASS, 0);
        SquareTile squareTile2 = new SquareTile(TileType.GRASS, 0);
        Tile tile1 = new Tile(1, new SquareTile[]{squareTile1, squareTile2});
        Tile tile2 = new Tile(1, new SquareTile[]{squareTile1, squareTile2});

        assertNotNull(tile1);
        assertNotNull(tile2);
        assertTrue(tile2.equals(tile1));
    }

    @Test
    public void reverseSquareTileOrder() {
        SquareTile squareTile1 = new SquareTile(TileType.GRASS, 0);
        SquareTile squareTile2 = new SquareTile(TileType.GRASS, 0);
        Tile tile1 = new Tile(1, new SquareTile[]{squareTile1, squareTile2});
        Tile tile2 = new Tile(1, new SquareTile[]{squareTile2, squareTile1});

        assertNotNull(tile1);
        assertNotNull(tile2);
        assertTrue(tile2.equals(tile1));
    }

    @Test
    public void reverseSquareTileOrder_fail() {
        SquareTile squareTile1 = new SquareTile(TileType.GRASS, 0);
        SquareTile squareTile2 = new SquareTile(TileType.GRASS, 0);
        Tile tile1 = new Tile(1, new SquareTile[]{squareTile1, squareTile2});
        Tile tile2 = new Tile(1, new SquareTile[]{squareTile2, squareTile2});

        assertNotNull(tile1);
        assertNotNull(tile2);
        assertFalse(tile2.equals(tile1));
    }
}
