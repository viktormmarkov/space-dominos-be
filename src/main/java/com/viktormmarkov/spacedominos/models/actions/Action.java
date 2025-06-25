package com.viktormmarkov.spacedominos.models.actions;

import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Action {
    protected String playerId;
    protected ActionTypeEnum actionType;

    public boolean isValid() {
        // Default implementation, can be overridden by subclasses
        throw new UnsupportedOperationException("isValid method not implemented for this action type");
    }
}
