package com.viktormmarkov.spacedominos.models.game.actions;

import com.viktormmarkov.spacedominos.models.game.board.Position;
import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;
import com.viktormmarkov.spacedominos.models.game.board.Tile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Override
    public boolean isValid() {
        boolean noNullPositions = position1 != null && position2 != null;
        boolean hasNeighbouringPositions = noNullPositions && position1.hasNeighbor(position2);
        boolean hasTile = tile != null;
        boolean tileExists = hasTile && tile.isExistingTile();

        return tileExists && hasNeighbouringPositions;
    }
}
