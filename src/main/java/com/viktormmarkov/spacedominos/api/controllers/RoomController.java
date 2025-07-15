package com.viktormmarkov.spacedominos.api.controllers;

import com.viktormmarkov.spacedominos.models.Response;
import com.viktormmarkov.spacedominos.models.dto.JoinRoom;
import com.viktormmarkov.spacedominos.models.dto.LeaveRoom;
import com.viktormmarkov.spacedominos.models.lobby.Room;
import com.viktormmarkov.spacedominos.services.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class RoomController {
    private final RoomService roomService;
    private final SimpMessagingTemplate messaging;

    private Room createRoom(Room room) {
        log.info("Creating room: {}", room);
        Room r = roomService.create(room);
        System.out.println("Room created: " + r.getId() + r.getName() + r.getHostId());
        return r;
    }

    @MessageMapping("/room/create")
    public void handleCreateRoomMessage(Room room) {
        // Create the room
        Room r = createRoom(room);
        messaging.convertAndSend("/topic/rooms/", r);
    }

    @GetMapping("/api/rooms")
    public List<Room> getGameLobbies() {
        return roomService.findAll();
    }

    @PostMapping("/api/rooms")
    public Room handleCreateRoomPost(@RequestBody Room room) {
        log.info("Creating room via POST: {}", room);

        return createRoom(room);
    }

    @PostMapping("/api/rooms/{id}/join")
    public Optional<Room> handleCreateRoomPost(@PathVariable String id, @RequestBody JoinRoom joinRoom) {
        log.info("Joining room via POST: {}", joinRoom);

        try {
            Room room = roomService.joinPlayer(id, joinRoom.getUser());
            messaging.convertAndSend("/topic/rooms/" + id, room);
        } catch (Exception e) {
            log.error("Error joining room: {}", e.getMessage());
            throw new RuntimeException("Failed to join room", e);
        }

        return roomService.find(id);
    }

    @PostMapping("/api/rooms/{id}/leave")
    public Response handleLeaveRoomPost(@PathVariable String id, @RequestBody LeaveRoom leaveRoom) {
        log.info("User {} is leaving room {}", leaveRoom.getUser(), id);
        try {
            Room room = roomService.leavePlayer(id, leaveRoom.getUser());
            messaging.convertAndSend("/topic/rooms/" + id, room);
            return new Response("User left the room successfully");
        } catch (Exception e) {
            log.error("Error leaving room: {}", e.getMessage());
            return new Response("Failed to leave room: " + e.getMessage());
        }
    }
}
