package com.viktormmarkov.spacedominos.api.controllers;

import com.viktormmarkov.spacedominos.models.GameState;
import com.viktormmarkov.spacedominos.repositories.GameStateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameStateController {
    @CrossOrigin(origins = "*") // Allow cross-origin requests
    @GetMapping("/api/games/list")
    public List<String> getAllGameIds() {
        // logic to get all game ids from the game lobby
        // return an array of game ids
        System.out.println("Fetching all game IDs");
        return GameStateRepository.getAllGameIds();
    }

    @PostMapping("/api/games/start/{gameId}")
    public void startGame(@PathVariable String gameId) {
        GameStateRepository.initGameState(gameId);
        // get users from the game lobby
        // convert them to player
        // logic to start the game
        // create a topic for the game that would be used to send game updates
    }

    @PostMapping("/api/games/update/{gameId}")
    public void updateGameState(@PathVariable String gameId) {
        GameState gameState = GameStateRepository.getGameState(gameId);
        gameState.setTurnCounter(gameState.getTurnCounter() + 1);
        // it should update the game state in the GameStateRepository
    }

    @GetMapping("/api/games/state/{gameId}")
    public String getGameState(@PathVariable String gameId) {
        GameState gameState = GameStateRepository.getGameState(gameId);
        return gameState.getTurnCounter() + " - " + gameState.getGameId();
    }
}
