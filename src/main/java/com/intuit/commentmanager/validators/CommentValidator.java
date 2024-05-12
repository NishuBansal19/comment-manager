package com.intuit.commentmanager.validators;

import com.intuit.commentmanager.dto.inbound.Comment;
import com.intuit.commentmanager.exceptions.CommentNotAllowedException;
import com.intuit.commentmanager.exceptions.InvalidInputException;
import com.intuit.commentmanager.repository.CommentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentValidator {

    @Autowired
    CommentRepository commentRepository;

    public void validateInputComment(Comment comment) {
        if(StringUtils.isBlank(comment.getContent())) {
            throw new CommentNotAllowedException("Comment content can not be null");
        } else if(comment.getPostId() <=0 || comment.getProfileId() <= 0) {
            throw new CommentNotAllowedException("Please select the correct post and profile");
        }
        if(comment.getId() > 0) {
            validateCommentOwner(comment.getId(), comment.getProfileId());
        }
    }

    public void validateCommentOwner(long id, long commenterId) {
        Optional<com.intuit.commentmanager.entity.Comment> savedCommentOptional = commentRepository.findById(id);
        if(savedCommentOptional.isEmpty()) {
            throw new InvalidInputException("Please provide valid comment id");
        }
        com.intuit.commentmanager.entity.Comment savedComment = savedCommentOptional.get();
        if(commenterId != savedComment.getCommentedBy().getId()) {
            throw new CommentNotAllowedException("You can not edit this comment, as you are not the owner");
        }
    }
}
