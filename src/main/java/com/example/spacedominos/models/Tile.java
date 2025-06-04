package com.example.spacedominos.models;

import com.example.spacedominos.enums.TileType;
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
