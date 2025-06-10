package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;

public class ChooseTileAction extends Action {
    private int draftTileIndex;
    private GameTile gameTile;
    public ChooseTileAction(String playerId, int draftTileIndex, GameTile gameTile) {
        super(playerId, ActionTypeEnum.CHOOSE_TILE);
        this.draftTileIndex = draftTileIndex;
        this.gameTile = gameTile;
    }
}
