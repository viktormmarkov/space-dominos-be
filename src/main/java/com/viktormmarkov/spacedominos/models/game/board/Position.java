package com.viktormmarkov.spacedominos.models.game.board;

public record Position(int x, int y) {
    public boolean hasNeighbor(Position other) {
        return (Math.abs(this.x - other.x) == 1 && this.y == other.y) || (Math.abs(this.y - other.y) == 1 && this.x == other.x);
    }
}
