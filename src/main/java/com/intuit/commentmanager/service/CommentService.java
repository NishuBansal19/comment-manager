package com.intuit.commentmanager.service;

import com.intuit.commentmanager.dto.inbound.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface CommentService {

    public Comment saveComment(Comment comment);

    public List<Comment> getComments();

    public Page<Comment> getCommentsByPostId(long postId, Pageable pageable);

    public Page<Comment> getRepliesByParentCommentId(long parentCommentId, Pageable pageable);

    public Comment getComment(long id);

    public void deleteCommentById(long id);
}
