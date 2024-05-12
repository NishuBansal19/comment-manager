package com.intuit.commentmanager.dto.inbound;

import lombok.Data;

import java.util.Date;

@Data
public class ActionInput {

    private long id; // Not using this id for update, client might not have it
    private String actionType;
    private boolean actionState;
    private long commentId;
    private long profileId; // update will happen using commentId and profileId
    private Date createdDate;
    private Date modifiedDate;
}
