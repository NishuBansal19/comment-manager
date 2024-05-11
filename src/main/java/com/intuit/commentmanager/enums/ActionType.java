package com.intuit.commentmanager.enums;

import com.intuit.commentmanager.exceptions.InvalidInputException;
import lombok.ToString;

import java.util.stream.Stream;

@ToString
public enum ActionType {
    LIKE,
    DISLIKE;

    public static ActionType lookUpType(String actionType) {
        return Stream.of(ActionType.values())
                .filter(type -> type.name().equalsIgnoreCase(actionType))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException("No matching action type found"));
    }
}
