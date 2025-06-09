package com.viktormmarkov.spacedominos.domain.enitities;

public class Board {

    boolean canPlaceTile(Position position, Tile tile) {
        Spot space = tile.evaluateSpot(position);
        return isFreeSpot(space);
    }

    private boolean isFreeSpot(Spot spot) {
        return false;
    }
}
