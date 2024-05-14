package com.intuit.commentmanager.dto.outbound;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ActionCount implements Serializable {
    private long likes;
    private long dislikes;
}
