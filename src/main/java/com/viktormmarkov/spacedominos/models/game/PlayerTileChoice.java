package com.viktormmarkov.spacedominos.models.game;

import com.viktormmarkov.spacedominos.models.game.board.Tile;
import lombok.Getter;

@Getter
public class PlayerTileChoice {
    Tile pickedTile;
    int draftIndex;

    public PlayerTileChoice(Tile pickedTile, int draftIndex) {
        this.pickedTile = pickedTile;
        this.draftIndex = draftIndex;
    }
}
