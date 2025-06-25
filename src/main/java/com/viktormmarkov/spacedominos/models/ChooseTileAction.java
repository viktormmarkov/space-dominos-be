package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChooseTileAction extends Action {
    private int draftTileIndex;
    private Tile tile;
    public ChooseTileAction(String playerId, int draftTileIndex, Tile tile) {
        super(playerId, ActionTypeEnum.CHOOSE_TILE);
        this.draftTileIndex = draftTileIndex;
        this.tile = tile;
    }
}
