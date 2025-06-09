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
    private Tile[][] tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public static void drawBoardMove(Board board, Position position1, Position position2, GameTile gameTile) {
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
              if (x == position1.x() && y == position1.y()) {
                    System.out.print(gameTile.getTiles()[0].getType().ordinal() + ' '); // Tile 1
                } else if (x == position2.x() && y == position2.y()) {
                    System.out.print(gameTile.getTiles()[1].getType().ordinal() + ' '); // Tile 2
                } else {
                    System.out.print(". "); // Empty space
                }
            }
            System.out.println();
        }
    }

    private boolean isPositionValid(Position position, Tile tile) {
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
                    Tile neighborTile = tiles[neighbor.x()][neighbor.y()];
                    if (neighborTile != null && (neighborTile.getType().equals(tile.getType()) || neighborTile.getType().equals(TileType.CASTLE))) {
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
        // Initialize the player's castle at the center of the board
        int centerX = width / 2;
        int centerY = height / 2;

        // Create a castle tile (assuming a TileType for castle)
        Tile castleTile = new Tile(TileType.CASTLE, 0); // Assuming TileType.CASTLE is defined
        tiles[centerX][centerY] = castleTile;
        return new Position(centerX, centerY);
    }

    public void placeGameTile(Position pos1, Position pos2, GameTile gameTile) {
        if (pos1.x() < 0 || pos1.x() >= width || pos1.y() < 0 || pos1.y() >= height ||
            pos2.x() < 0 || pos2.x() >= width || pos2.y() < 0 || pos2.y() >= height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        if (gameTile.getTiles().length != 2) {
            throw new IllegalArgumentException("GameTile must contain exactly two tiles");
        }
        if (tiles[pos1.x()][pos1.y()] != null || tiles[pos2.x()][pos2.y()] != null) {
            throw new IllegalStateException("Positions already occupied");
        }
        if (!isPositionValid(pos1, gameTile.getTiles()[0]) && !isPositionValid(pos2, gameTile.getTiles()[1])) {
            throw new IllegalArgumentException("Cannot place tiles at the specified positions");
        }

        tiles[pos1.x()][pos1.y()] = gameTile.getTiles()[0];
        tiles[pos2.x()][pos2.y()] = gameTile.getTiles()[1];
    }

    public Tile getTileAt(Position position) {
        if (position.x() < 0 || position.x() >= width || position.y() < 0 || position.y() >= height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return tiles[position.x()][position.y()];
    }

    public void wipeBoard() {
        for (Tile[] row : tiles) {
            Arrays.fill(row, null); // Clear each tile in the row
        }
    }

    public void drawBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = tiles[x][y];
                if (tile != null) {
                    System.out.print(tile.getType().ordinal() + " "); // Assuming TileType has a method getSymbol()
//                    System.out.print("1 ");
                } else {
                    System.out.print(". "); // Empty space
                }
            }
            System.out.println();
        }
    }

}
