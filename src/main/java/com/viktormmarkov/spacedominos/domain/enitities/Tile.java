package com.viktormmarkov.spacedominos.domain.enitities;

import com.viktormmarkov.spacedominos.domain.enums.TileType;

import java.util.Objects;

public class Tile {

    private final TileType left;
    private final TileType right;
    private int rotation = 0; // can have 4 different positions.
    //todo: other properties?

    public Tile(TileType left, TileType right) {
        this.left = left;
        this.right = right;
    }

    public void rotateClockwise() {
        rotation++;
        if (rotation > 3) {
            //after 3rd position we return to the first position
            rotation = 0;
        }
    }

    public void rotateCounterclockwise() {
        rotation--;
        if (rotation < 0) {
            //if we rotate counterclockwise from the first position we'll get 3rd
            rotation = 3;
        }
    }

    public void flip() {
        rotation = rotation == 1 ? 3 : Math.abs(rotation - 2);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return left == tile.left && right == tile.right;//todo: should we take into account the position?
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public Spot evaluateSpot(Position position) {
        return null;
    }
}
