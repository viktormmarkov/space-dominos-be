package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.domain.enums.TileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class SquareTile {
    private TileType type;
    private int numberOfCrowns;
}
