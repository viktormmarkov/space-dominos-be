package com.example.spacedominos.controllers;

import com.example.spacedominos.models.Message;
import com.example.spacedominos.models.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class WebSocketController {

    @MessageMapping("/process")
    @SendTo("/topic/game-events")
    @CrossOrigin(origins = "*") // Allow cross-origin requests
    public Response processMessage(Message message) {
        // Logic to process the incoming message
        // This could involve parsing the message, updating game state, etc.
        System.out.println("Received message: " + message.getContent());
        return new Response(message.getContent());
        // Here you would typically send a response back to the client or update the game state
    }
}
