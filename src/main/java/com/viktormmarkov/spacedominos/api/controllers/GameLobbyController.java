package com.viktormmarkov.spacedominos.api.controllers;

import com.viktormmarkov.spacedominos.models.lobby.GameLobby;
import com.viktormmarkov.spacedominos.models.lobby.User;
import com.viktormmarkov.spacedominos.services.GameLobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api/game-lobby")
@RestController
public class GameLobbyController {
    private final GameLobbyService gameLobbyService;

    @Autowired
    public GameLobbyController(GameLobbyService gameLobbyService) {
        this.gameLobbyService = gameLobbyService;
    }

    @GetMapping
    public List<GameLobby> getGameLobbies() {
        return (List<GameLobby>) gameLobbyService.findAllGameLobbies();
    }

    @PostMapping
    public GameLobby createGameLobby(@RequestBody GameLobby gameLobby) {
        // id should be system generated and not an argument
        // should get the user and create a player object
        // This method should create a new game lobby.
        // For now, we will return a new GameLobby object with the provided parameters.
        return gameLobbyService.saveGameLobby(gameLobby);
    }

    public GameLobby joinGameLobby(String gameId, User user) {
        // This method should allow a player to join an existing game lobby.
        // For now, we will return a new GameLobby object with the provided gameId and player.
        return new GameLobby("Game Name", "password");
    }

    public String startGame(String gameId) {
        // This method should start the game with the given gameId.
        // For now, we will return a success message.
        return "Game with ID " + gameId + " has started successfully.";
    }

    @GetMapping("/{id}")
    public GameLobby getGameLobbyById(@PathVariable int id) {
        // This method should return a game lobby by its ID.
        return gameLobbyService.findGameLobbyById(id);
    }
}