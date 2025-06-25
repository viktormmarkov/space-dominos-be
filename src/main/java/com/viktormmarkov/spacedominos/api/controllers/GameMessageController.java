package com.viktormmarkov.spacedominos.api.controllers;

import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;
import com.viktormmarkov.spacedominos.models.Response;
import com.viktormmarkov.spacedominos.models.game.actions.Action;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameMessageController {

    @MessageMapping("/game/{id}")
    @SendTo("/topic/game/{id}")
    public Response processGameMove(@DestinationVariable String id, Action action) {
        System.out.println("Game event hit");
        if (action.getActionType() == ActionTypeEnum.CHOOSE_TILE ) {
            return new Response(ActionTypeEnum.CHOOSE_TILE.toString());
        } else if (action.getActionType() == ActionTypeEnum.PLACE_TILE) {
            return new Response(ActionTypeEnum.PLACE_TILE.toString());
        } else {
            return new Response("Unsupported action type");
        }
    }

    // this class should handle communication with client-side game logic
    // it should process game events, update game state, and send updates to clients
    // logic should be handled in the GameState model
}
