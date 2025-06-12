package com.viktormmarkov.spacedominos.services;

import com.viktormmarkov.spacedominos.models.GameState;
import com.viktormmarkov.spacedominos.models.Player;
import org.junit.jupiter.api.Test;

class GameActionValidatorServiceTest {
    @Test
    void isValidMove_chooseTile_success() {
        GameState gameState = new GameState(
                "gameId",
                new Player[]{
                        new Player("player1", "Player 1"),
                        new Player("player2", "Player 2")
                }
        );

        GameActionValidatorService validatorService = new GameActionValidatorService(gameState);
    }

    @Test
    void isValidMove_chooseTile_error() {
    }


    @Test
    void testIsValidMove_placeTile_success() {
    }

    @Test
    void testIsValidMove_placeTile_fail() {
    }
}