package com.viktormmarkov.spacedominos.models.game.actions;

import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;
import com.viktormmarkov.spacedominos.models.game.board.Tile;
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

    @Override
    public boolean isValid() {
        boolean hasTile = tile != null;
        boolean tileExists = hasTile && tile.isExistingTile();
        boolean validIndex = draftTileIndex >= 0 && draftTileIndex < 4; // Assuming there are always 4 draft tiles

        return tileExists && validIndex;
    }
}
