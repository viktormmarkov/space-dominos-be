package com.viktormmarkov.spacedominos.helpers;

import com.viktormmarkov.spacedominos.models.game.board.Position;
import com.viktormmarkov.spacedominos.models.game.board.Board;
import com.viktormmarkov.spacedominos.models.game.board.Tile;

public final class BoardDrawer {
    private BoardDrawer() {
        // Prevent instantiation
    }

    public static void drawBoardMove(Board board, Position position1, Position position2, Tile tile) {
        drawBoard(board);
        System.out.println("Placing game tile at positions: " + position1 + " and " + position2);
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (x == position1.x() && y == position1.y()) {
                    System.out.print(tile.getSquareTiles()[0].getType().ordinal() + " "); // Tile 1
                } else if (x == position2.x() && y == position2.y()) {
                    System.out.print(tile.getSquareTiles()[1].getType().ordinal() + " "); // Tile 2
                } else {
                    System.out.print(". "); // Empty space
                }
            }
            System.out.println();
        }

    }

    public static void drawBoard(Board board) {
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (board.getTileAt(new Position(x, y)) != null) {
                    System.out.print(board.getTileAt(new Position(x, y)).getType().ordinal() + " ");
                } else {
                    System.out.print(". "); // Empty space
                }
            }
            System.out.println();
        }
    }
}
