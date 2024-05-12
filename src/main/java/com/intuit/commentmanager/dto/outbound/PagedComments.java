package com.intuit.commentmanager.dto.outbound;

import com.intuit.commentmanager.entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class PagedComments {

    private int totalCount;

    private List<Comment> commentList;
}
