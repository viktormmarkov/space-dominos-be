package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enums.TileType;
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
    private TileType type;
    private int numberOfCrowns;
}
