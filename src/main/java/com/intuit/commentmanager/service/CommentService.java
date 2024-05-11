package com.intuit.commentmanager.service;

import com.intuit.commentmanager.dto.inbound.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {

    public Comment saveComment(Comment comment);

    public List<Comment> getComments();

    public Comment getComment();
}
