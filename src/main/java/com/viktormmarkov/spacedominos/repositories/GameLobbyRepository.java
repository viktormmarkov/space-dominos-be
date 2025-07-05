package com.viktormmarkov.spacedominos.repositories;

import com.viktormmarkov.spacedominos.models.lobby.GameLobby;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameLobbyRepository extends CrudRepository<GameLobby, Integer> {

    // This repository will automatically provide CRUD operations for GameLobby entities.
    // Additional custom methods can be defined here if needed.
}
