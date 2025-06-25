package com.viktormmarkov.spacedominos.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String id;
    private String name;
    private String secondaryId; // Assuming secondaryId is a unique
    PlayerTileChoice currentTileChoice;
    PlayerTileChoice nextTileChoice;

    public Player(String id, String name) {
        this.id = id;
        this.secondaryId = id + "_secondary"; // Assuming secondaryId is derived from id
        this.name = name;
    }

    public void setNextTileChoice(Tile tile, int index) {
        this.nextTileChoice = new PlayerTileChoice(tile, index);
    }

    public void updateCurrentTileChoice() {
        this.currentTileChoice = this.nextTileChoice;
        this.nextTileChoice = null;
    }

    public boolean canPickNextTile() {
        return this.nextTileChoice == null;
    }
}
