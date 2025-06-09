package com.viktormmarkov.spacedominos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Board {
    private int width;
    private int height;
    private Tile[][] tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public void placeGameTile(int x1, int y1, int x2, int y2, GameTile gameTile) {
        if (x1 < 0 || x1 >= width || y1 < 0 || y1 >= height ||
            x2 < 0 || x2 >= width || y2 < 0 || y2 >= height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        if (tiles[x1][y1] != null || tiles[x2][y2] != null) {
            throw new IllegalStateException("Tiles already occupied");
        }
        tiles[x1][y1] = gameTile.getTiles()[0];
        tiles[x2][y2] = gameTile.getTiles()[1];
    }
}
