package com.viktormmarkov.spacedominos.models.game.actions;

import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;
import com.viktormmarkov.spacedominos.models.game.board.Tile;
import com.viktormmarkov.spacedominos.models.game.board.TilePosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceTileAction extends Action {
    private TilePosition tilePosition;
    private Tile tile;
    public PlaceTileAction(String playerId, TilePosition tilePosition, Tile tile) {
        super(playerId, ActionTypeEnum.PLACE_TILE);
        if (tilePosition == null || !tile.isExistingTile()) {
            throw new IllegalArgumentException("Tile position cannot be null and tile must be an existing tile.");
        }
        this.tilePosition = tilePosition;
        this.tile = tile;
    }
}
