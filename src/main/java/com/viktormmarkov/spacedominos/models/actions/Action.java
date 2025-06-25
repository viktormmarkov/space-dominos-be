package com.viktormmarkov.spacedominos.models.actions;

import com.viktormmarkov.spacedominos.domain.enums.ActionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Action {
    protected String playerId;
    protected ActionTypeEnum actionType;
}
