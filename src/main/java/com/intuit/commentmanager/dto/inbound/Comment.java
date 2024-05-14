package com.intuit.commentmanager.dto.inbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intuit.commentmanager.dto.outbound.ActionCount;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    private long id; // update comment id non null
    private long postId;
    private String content;
    private long profileId;
    private String userName;
    private long parentCommentId;
    private boolean childCommentPresent; // to show view reply button
    private ActionCount actionCount;
    private Date createdDate;
    private Date modifiedDate;
}
