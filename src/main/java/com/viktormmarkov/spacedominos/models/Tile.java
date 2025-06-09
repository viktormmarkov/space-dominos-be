package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enums.TileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tile {
    private TileType type;
    private int multiplier;
}
