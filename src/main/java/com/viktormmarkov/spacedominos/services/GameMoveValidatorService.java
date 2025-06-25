package com.viktormmarkov.spacedominos.services;

import com.viktormmarkov.spacedominos.domain.enums.GamePhaseEnum;
import com.viktormmarkov.spacedominos.models.actions.Action;
import com.viktormmarkov.spacedominos.models.actions.ChooseTileAction;
import com.viktormmarkov.spacedominos.models.GameState;
import com.viktormmarkov.spacedominos.models.actions.PlaceTileAction;

public class GameMoveValidatorService {
    private final GameState gameState;
    public GameMoveValidatorService(GameState gameState) {
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
        return isPlayerTurn(action.getPlayerId()) && isGameInCorrectPhase(GamePhaseEnum.CHOOSE_TILES) && action.isValid();
    }

    public boolean isValidMove(PlaceTileAction action) {
        return isPlayerTurn(action.getPlayerId()) && isGameInCorrectPhase(GamePhaseEnum.PLACE_TILES) && action.isValid();
    }

}
