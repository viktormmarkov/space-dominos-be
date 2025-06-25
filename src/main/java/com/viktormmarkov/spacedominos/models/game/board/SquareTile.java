package com.viktormmarkov.spacedominos.models.game.board;

import com.viktormmarkov.spacedominos.domain.enums.TileTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SquareTile {
    public SquareTile(SquareTile tile) {
        this.type = tile.getType();
        this.numberOfCrowns = tile.getNumberOfCrowns();
    }
    private TileTypeEnum type;
    private int numberOfCrowns;
}
