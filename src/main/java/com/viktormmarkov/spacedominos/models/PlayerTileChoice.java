package com.viktormmarkov.spacedominos.models;

public class PlayerTileChoice {
    Tile pickedTile;
    int draftIndex;

    public PlayerTileChoice(Tile pickedTile, int draftIndex) {
        this.pickedTile = pickedTile;
        this.draftIndex = draftIndex;
    }
}
