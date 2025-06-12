package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enums.TileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.viktormmarkov.spacedominos.domain.enitities.Position;

import java.util.Arrays;

@AllArgsConstructor
@Getter
@Setter
public class Board {
    private int width;
    private int height;
    private SquareTile[][] squareTiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.squareTiles = new SquareTile[width][height];
    }

    private boolean isPositionValid(Position position, SquareTile squareTile) {
        // check the neighboring positions based on position input.
        int x = position.x();
        int y = position.y();
        Position north = new Position(x, y + 1);
        Position south = new Position(x, y - 1);
        Position east = new Position(x + 1, y);
        Position west = new Position(x - 1, y);
        boolean hasMatchingNeighbor = false;
        Position[] neighbors = {north, south, east, west};
        try {
            for (Position neighbor : neighbors) {
                if (neighbor.x() >= 0 && neighbor.x() < width && neighbor.y() >= 0 && neighbor.y() < height) {
                    SquareTile neighborSquareTile = squareTiles[neighbor.x()][neighbor.y()];
                    if (neighborSquareTile != null && (neighborSquareTile.getType().equals(squareTile.getType()) || neighborSquareTile.getType().equals(TileType.CASTLE))) {
                        hasMatchingNeighbor = true;
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false; // Position is out of bounds
        }
        return hasMatchingNeighbor;
    }

    public Position initPlayerCastle() {
        int centerX = width / 2;
        int centerY = height / 2;

        // Create a castle tile (assuming a TileType for castle)
        SquareTile castleSquareTile = new SquareTile(TileType.CASTLE, 0); // Assuming TileType.CASTLE is defined
        squareTiles[centerX][centerY] = castleSquareTile;
        return new Position(centerX, centerY);
    }

    public void placeGameTile(Position pos1, Position pos2, Tile tile) {
        if (pos1.x() < 0 || pos1.x() >= width || pos1.y() < 0 || pos1.y() >= height ||
            pos2.x() < 0 || pos2.x() >= width || pos2.y() < 0 || pos2.y() >= height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        if (tile.getSquareTiles().length != 2) {
            throw new IllegalArgumentException("GameTile must contain exactly two tiles");
        }
        if (squareTiles[pos1.x()][pos1.y()] != null || squareTiles[pos2.x()][pos2.y()] != null) {
            throw new IllegalStateException("Positions already occupied");
        }
        if (!isPositionValid(pos1, tile.getSquareTiles()[0]) && !isPositionValid(pos2, tile.getSquareTiles()[1])) {
            throw new IllegalArgumentException("Cannot place tiles at the specified positions");
        }

        squareTiles[pos1.x()][pos1.y()] = tile.getSquareTiles()[0];
        squareTiles[pos2.x()][pos2.y()] = tile.getSquareTiles()[1];
    }

    public SquareTile getTileAt(Position position) {
        if (position.x() < 0 || position.x() >= width || position.y() < 0 || position.y() >= height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return squareTiles[position.x()][position.y()];
    }

    public void wipeBoard() {
        for (SquareTile[] row : squareTiles) {
            Arrays.fill(row, null); // Clear each tile in the row
        }
    }
}
