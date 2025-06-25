package com.viktormmarkov.spacedominos.models.game.board;

import com.viktormmarkov.spacedominos.repositories.TilesRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Tile implements Cloneable {
    private int number;
    private SquareTile[] squareTiles;

    public Tile(int number, SquareTile[] squareTiles) {
        if (squareTiles == null || squareTiles.length != 2) {
            throw new IllegalArgumentException("Tile must contain exactly two square tiles.");
        }
        this.number = number;
        this.squareTiles = squareTiles;
    }

    public boolean isExistingTile() {
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
