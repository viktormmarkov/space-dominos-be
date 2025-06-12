package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enitities.Position;
import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;

public class PlaceTileAction extends Action {
    private Position position1;
    private Position position2;
    private Tile tile;
    public PlaceTileAction(String playerId, Position position1, Position position2, Tile tile) {
        super(playerId, ActionTypeEnum.PLACE_TILE);
        this.position1 = position1;
        this.position2 = position2;
        this.tile = tile;
    }
}
