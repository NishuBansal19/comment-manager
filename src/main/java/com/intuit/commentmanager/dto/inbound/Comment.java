package com.intuit.commentmanager.dto.inbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    private long postId;
    private String content;
    private long profileId;
    private long commentId;
    private long parentCommentId;
}
