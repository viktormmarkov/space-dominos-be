package com.viktormmarkov.spacedominos.models;

import com.viktormmarkov.spacedominos.repositories.TilesRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
public class Tile implements Cloneable {
    private int number;
    private SquareTile[] squareTiles;

    boolean isExistingTile() {
        Tile[] allTiles = TilesRepository.getTiles();
        if (number < 1 || number > allTiles.length) {
            return false;
        }
        Tile tileSelected = allTiles[number - 1];
        return this.equals(tileSelected);
    };

    public boolean equals(Tile that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        if (number != that.number) return false;
        return Arrays.equals(squareTiles, that.squareTiles) || Arrays.equals(squareTiles, new SquareTile[]{that.squareTiles[1], that.squareTiles[0]});
    }

    @Override
    public Tile clone() {
        try {
            Tile cloned = (Tile) super.clone();
            cloned.setSquareTiles(Arrays.stream(this.squareTiles)
                    .map(SquareTile::new)
                    .toArray(SquareTile[]::new));
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }
}
