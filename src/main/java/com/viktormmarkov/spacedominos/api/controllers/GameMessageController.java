package com.viktormmarkov.spacedominos.api.controllers;

import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;
import com.viktormmarkov.spacedominos.models.Response;
import com.viktormmarkov.spacedominos.models.game.actions.Action;
import com.viktormmarkov.spacedominos.services.GameMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class GameMessageController {
    private final GameMessageService gameMessageService;
    @MessageMapping("/game/{id}")
    @SendTo("/topic/game/{id}")
    public Response processGameMessage(@DestinationVariable String id, Action action) {
        System.out.println("Game event hit");
        try {
            gameMessageService.processMessage(id, action);
            return new Response("Game event received: " + action.getActionType() + " for game ID: " + id);
        } catch (Exception e) {
            System.err.println("Error processing game message: " + e.getMessage());
            return new Response("Error processing game message: " + e.getMessage());
        }
    }
}
