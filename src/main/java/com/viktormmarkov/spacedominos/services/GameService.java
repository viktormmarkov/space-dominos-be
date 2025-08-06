package com.viktormmarkov.spacedominos.services;

import com.viktormmarkov.spacedominos.models.game.GameState;
import com.viktormmarkov.spacedominos.models.lobby.Room;
import com.viktormmarkov.spacedominos.repositories.GameStateRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class GameService {
    public GameState startGameInRoom(Room room) {
        // Logic to start the game
        GameState gameState = GameStateRepository.initGameStateFromRoom(room);
        System.out.println("Starting game with ID: " + room.getId());
        return gameState;
    }
}
