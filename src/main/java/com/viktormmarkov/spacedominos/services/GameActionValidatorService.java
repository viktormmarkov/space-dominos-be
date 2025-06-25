package com.viktormmarkov.spacedominos.services;

import com.viktormmarkov.spacedominos.domain.enums.GamePhaseEnum;
import com.viktormmarkov.spacedominos.models.actions.Action;
import com.viktormmarkov.spacedominos.models.actions.ChooseTileAction;
import com.viktormmarkov.spacedominos.models.GameState;
import com.viktormmarkov.spacedominos.models.actions.PlaceTileAction;

public class GameActionValidatorService {
    private final GameState gameState;
    public GameActionValidatorService(GameState gameState) {
        this.gameState = gameState;
    }
    private boolean isPlayerTurn(String playerId) {
        // Check if it's the player's turn
        return gameState.getCurrentPlayerId().equals(playerId);
    }

    private boolean isGameInCorrectPhase(GamePhaseEnum phase) {
        // Check if the game is in a state that allows actions (e.g., not finished)
        return gameState.getGamePhase() == phase;
    }

    public boolean isValidMove(Action action) {
        return false;
    }

    public boolean isValidMove(ChooseTileAction action) {
        // Validate the ChooseTileAction
        return isPlayerTurn(action.getPlayerId()) && isGameInCorrectPhase(GamePhaseEnum.CHOOSE_TILES);
    }

    public boolean isValidMove(PlaceTileAction action) {
        // Validate the PlaceTileAction
        // Check if the tile can be placed on the board at the specified position
        // Check if the player has enough resources to place the tile
        // Check if the tile is valid according to game rules
        return isPlayerTurn(action.getPlayerId()) && isGameInCorrectPhase(GamePhaseEnum.PLACE_TILES);
    }

}
