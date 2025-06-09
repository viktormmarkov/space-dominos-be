package com.viktormmarkov.spacedominos.domain.enitities;

import com.viktormmarkov.spacedominos.domain.enums.TileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    public void canInstantiateTile() {
        Tile  tile = new Tile(TileType.GRASS, TileType.WATER);
        assertNotNull(tile);
    }

    @Test
    public void canCompare() {
        Tile  tile = new Tile(TileType.GRASS, TileType.WATER);
        Tile tile2 = new Tile(TileType.GRASS, TileType.WATER);
        assertEquals(tile, tile2);
    }

    @Test
    public void canRotate() {
        Tile  tile = new Tile(TileType.GRASS, TileType.WATER);
        tile.rotateClockwise();
    }
}