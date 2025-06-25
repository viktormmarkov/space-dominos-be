package com.viktormmarkov.spacedominos.repositories;

import com.viktormmarkov.spacedominos.models.game.board.Tile;
import com.viktormmarkov.spacedominos.models.game.board.SquareTile;
import com.viktormmarkov.spacedominos.domain.enums.TileType;

import java.util.Arrays;

public final class TilesRepository {
    static Tile[] tiles = {
        new Tile(1, new SquareTile[]{
            new SquareTile(TileType.GRASS, 0),
            new SquareTile(TileType.WATER, 0),
        }),
        new Tile(2, new SquareTile[]{
            new SquareTile(TileType.SWAMP, 1),
            new SquareTile(TileType.GRASS, 1),
        }),
        new Tile(3, new SquareTile[]{
            new SquareTile(TileType.MOUNTAIN, 0),
            new SquareTile(TileType.WATER, 1),
        }),
        new Tile(4, new SquareTile[]{
                new SquareTile(TileType.MOUNTAIN, 0),
                new SquareTile(TileType.WATER, 1),
        }),
        new Tile(5, new SquareTile[]{
            new SquareTile(TileType.GRASS, 2),
            new SquareTile(TileType.SWAMP, 0),
        }),
        new Tile(6, new SquareTile[]{
            new SquareTile(TileType.SWAMP, 1),
            new SquareTile(TileType.GRASS, 0),
        }),
        new Tile(7, new SquareTile[]{
            new SquareTile(TileType.MOUNTAIN, 1),
            new SquareTile(TileType.WATER, 0),
        }),
        new Tile(8, new SquareTile[]{
                new SquareTile(TileType.MOUNTAIN, 1),
                new SquareTile(TileType.WATER, 0),
        }),
        new Tile(9, new SquareTile[]{
                new SquareTile(TileType.MOUNTAIN, 1),
                new SquareTile(TileType.WATER, 0),
        }),
        // Add more tiles as needed
    };

    public static Tile[] getTiles() {
        return tiles;
    }


    public static Tile[] getShuffledTiles() {
        Tile[] shuffledTiles = tiles.clone();
        java.util.Collections.shuffle(Arrays.asList(shuffledTiles));
        return shuffledTiles;
    }
}
