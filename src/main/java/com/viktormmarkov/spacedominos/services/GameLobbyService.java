package com.viktormmarkov.spacedominos.services;

import com.viktormmarkov.spacedominos.models.lobby.GameLobby;
import com.viktormmarkov.spacedominos.repositories.GameLobbyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameLobbyService {
    private final GameLobbyRepository gameLobbyRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public GameLobbyService(GameLobbyRepository gameLobbyRepository, RedisTemplate<String, Object> redisTemplate) {
        this.gameLobbyRepository = gameLobbyRepository;
        this.redisTemplate = redisTemplate;
    }

    public GameLobby saveGameLobby(GameLobby gameLobby) {
        GameLobby saved = gameLobbyRepository.save(gameLobby);
        redisTemplate.opsForValue().set("gameLobby:" + saved.getId(), saved);
        return saved;
    }

    public void deleteGameLobby(int id) {
        gameLobbyRepository.deleteById(id);
    }

    public GameLobby findGameLobbyById(int id) {
        log.info("Finding game lobby with ID: {}", id);
        log.info("Checking Redis for game lobby with ID: {}", id);
        GameLobby cachedLobby = (GameLobby) redisTemplate.opsForValue().get("gameLobby:" + id);
        if (cachedLobby != null) {
            log.info("Found game lobby in Redis cache: {}", cachedLobby);
            return cachedLobby;
        }
        return gameLobbyRepository.findById(id).orElse(null);

    }

    public Iterable<GameLobby> findAllGameLobbies() {
        return gameLobbyRepository.findAll();
    }
}
