package com.viktormmarkov.spacedominos.models;

public class Player {
    private String id;
    private String name;
    PlayerTileChoice currentTileChoice;
    PlayerTileChoice nextTileChoice;

    public Player(String id, String name) {
        this.id = id;
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

    public PlayerTileChoice getCurrentTileChoice() {
        return currentTileChoice;
    }

    public PlayerTileChoice getNextTileChoice() {
        return nextTileChoice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
