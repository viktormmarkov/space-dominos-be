package com.example.spacedominos.controllers;

import com.example.spacedominos.models.GameLobby;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GamesController {
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
}
