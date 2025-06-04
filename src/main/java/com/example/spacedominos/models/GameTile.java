package com.example.spacedominos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameTile {
    private int number;
    private Tile[] tiles;
}
