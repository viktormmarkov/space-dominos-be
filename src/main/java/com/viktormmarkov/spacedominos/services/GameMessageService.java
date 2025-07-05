package com.viktormmarkov.spacedominos.services;

import com.viktormmarkov.spacedominos.models.game.GameState;
import com.viktormmarkov.spacedominos.models.game.actions.Action;
import com.viktormmarkov.spacedominos.models.game.actions.ChooseTileAction;
import com.viktormmarkov.spacedominos.models.game.actions.PlaceTileAction;
import com.viktormmarkov.spacedominos.repositories.GameStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GameMessageService {
    public GameState processMessage(String gameId, Action action) {
        GameState gameState = GameStateRepository.getGameState(gameId);
        GameMoveValidatorService gameMoveValidatorService = new GameMoveValidatorService(gameState);

        if (gameState == null) {
            throw new IllegalArgumentException("Game state not found for game ID: " + gameId);
        }
        switch (action.getActionType()) {
            case CHOOSE_TILE:
                ChooseTileAction chooseTileAction = (ChooseTileAction) action;
                if (gameMoveValidatorService.isValidMove(chooseTileAction)) {
                    gameState.chooseTile(chooseTileAction);
                } else {
                    throw new IllegalArgumentException("Move is not valid");
                }
                break;
            case PLACE_TILE:
                PlaceTileAction placeTileAction = (PlaceTileAction) action;
                if (gameMoveValidatorService.isValidMove(placeTileAction)) {
                    gameState.placeTile(placeTileAction);
                } else {
                    throw new IllegalArgumentException("Move is not valid");
                }
                break;
            default:
                throw new UnsupportedOperationException("Unsupported action type: " + action.getActionType());
        }

        return gameState;
    }
}
