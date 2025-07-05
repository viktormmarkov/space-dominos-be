package com.viktormmarkov.spacedominos.services;

import com.viktormmarkov.spacedominos.models.lobby.GameLobby;
import com.viktormmarkov.spacedominos.repositories.GameLobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameLobbyService {
    private final GameLobbyRepository gameLobbyRepository;

    @Autowired
    public GameLobbyService(GameLobbyRepository gameLobbyRepository) {
        this.gameLobbyRepository = gameLobbyRepository;
    }

    public GameLobby saveGameLobby(GameLobby gameLobby) {
        return gameLobbyRepository.save(gameLobby);
    }

    public void deleteGameLobby(int id) {
        gameLobbyRepository.deleteById(id);
    }

    public GameLobby findGameLobbyById(int id) {
        return gameLobbyRepository.findById(id).orElse(null);
    }

    public Iterable<GameLobby> findAllGameLobbies() {
        return gameLobbyRepository.findAll();
    }
}
