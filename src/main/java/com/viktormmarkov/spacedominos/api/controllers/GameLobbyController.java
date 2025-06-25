package com.viktormmarkov.spacedominos.api.controllers;

import com.viktormmarkov.spacedominos.models.lobby.GameLobby;
import com.viktormmarkov.spacedominos.models.lobby.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameLobbyController {
    @CrossOrigin(origins = "*")
    @GetMapping("/api/games")
    public List<GameLobby> getGameLobbies() {
        // This method should return a list of games.
        // For now, we will return an empty list.
        try {
            return List.of(); // Return an empty list in case of an error
        } catch (Exception e) {
            System.out.println("An error occurred while fetching games: " + e.getMessage());
            // Handle any exceptions that may occur
            e.printStackTrace();
            return List.of(); // Return an empty list in case of an error
        }
    }

    @PostMapping("/api/games")
    public GameLobby createGameLobby(String gameLobbyName, String password) {
        // id should be system generated and not an argument
        // should get the user and create a player object
        // This method should create a new game lobby.
        // For now, we will return a new GameLobby object with the provided parameters.
        return new GameLobby(gameLobbyName, password);
    }

    public GameLobby joinGameLobby(String gameId, User user) {
        // This method should allow a player to join an existing game lobby.
        // For now, we will return a new GameLobby object with the provided gameId and player.
        return new GameLobby("Game Name", "password");
    }

    public String starGame(String gameId) {
        // This method should start the game with the given gameId.
        // For now, we will return a success message.
        return "Game with ID " + gameId + " has started successfully.";
    }
}