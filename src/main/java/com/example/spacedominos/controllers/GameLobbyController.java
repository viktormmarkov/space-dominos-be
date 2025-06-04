package com.example.spacedominos.controllers;

import com.example.spacedominos.models.GameLobby;
import com.example.spacedominos.models.Player;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class GameLobbyController {
    @CrossOrigin(origins = "*")
    @RequestMapping("/api/games")
    public List<GameLobby> getGames() {
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

    public GameLobby createGameLobby(String name, String password) {
        // id should be system generated and not an argument
        // should get the user and create a player object
        // This method should create a new game lobby.
        // For now, we will return a new GameLobby object with the provided parameters.
        String gameId = "generated-game-id"; // This should be replaced with actual ID generation logic
        return new GameLobby(gameId, name, password, new Date(), null, null, null, new Player[0]);
    }

    public GameLobby joinGameLobby(String gameId, Player player) {
        // This method should allow a player to join an existing game lobby.
        // For now, we will return a new GameLobby object with the provided gameId and player.
        return new GameLobby(gameId, "Game Name", "password", null, null, null, player, new Player[]{player});
    }
}