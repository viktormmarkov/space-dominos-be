package com.viktormmarkov.spacedominos.models.game.board;

import lombok.Getter;

@Getter
public class TilePosition {
    Position position1;
    Position position2;

    public TilePosition(Position position1, Position position2) {
        if (!position1.hasNeighbor(position2)) {
            throw new IllegalArgumentException("Positions must be neighbors");
        }
        this.position1 = position1;
        this.position2 = position2;
    }
}
