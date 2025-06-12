package com.viktormmarkov.spacedominos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tile {
    private int number;
    private SquareTile[] squareTiles;
}
