package com.viktormmarkov.spacedominos.services;

import com.viktormmarkov.spacedominos.models.lobby.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoomService {
    private final RedisTemplate<String, Room> redis;
    private final static String KEY_PREFIX = "room:";

    public RoomService (@Qualifier("roomRedisTemplate") RedisTemplate<String, Room> redis) {
        this.redis = redis;
    }

    public Room create(Room room) {
        try {
            String id = UUID.randomUUID().toString();
            log.info("Creating room with ID: {}", id);
            Room l = new Room(
                    id,
                    room.getName(),
                    room.getPassword(),
                    room.getHostId()
            );
            redis.opsForValue().set(KEY_PREFIX + id, l);
            return l;
        } catch (Exception e) {
            log.error("Error creating room: {}", e.getMessage());
            throw new RuntimeException("Failed to create room", e);
        }
    }

    public Optional<Room> find(String id) {
        return Optional.ofNullable(redis.opsForValue().get(KEY_PREFIX + id));
    }

    public void save(Room l) {
        redis.opsForValue().set(KEY_PREFIX + l.getId(), l);
    }

    public void delete(String id) {
        redis.delete(KEY_PREFIX + id);
    }

    public List<Room> findAll() {
        // If you need list of all lobbies, you could scan keys:
        Set<String> keys = redis.keys(KEY_PREFIX + "*");
        return keys.stream()
                .map(redis.opsForValue()::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Room joinPlayer(String roomId, int playerId) {
        Room room = find(roomId).orElseThrow(() -> new NoSuchElementException("Room not found"));
        room.joinPlayer(playerId);
        save(room);
        return room;
    }

    public Room leavePlayer(String roomId, int playerId) {
        Room room = find(roomId).orElseThrow(() -> new NoSuchElementException("Room not found"));
        room.leavePlayer(playerId);
        save(room);
        return room;
    }
}