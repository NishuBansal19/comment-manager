package com.intuit.commentmanager.service.impl;

import com.intuit.commentmanager.dto.inbound.Comment;
import com.intuit.commentmanager.entity.Post;
import com.intuit.commentmanager.entity.Profile;
import com.intuit.commentmanager.mappers.CommentMapper;
import com.intuit.commentmanager.repository.CommentRepository;
import com.intuit.commentmanager.repository.PostRepository;
import com.intuit.commentmanager.repository.ProfileRepository;
import com.intuit.commentmanager.service.CommentService;
import com.intuit.commentmanager.validators.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentValidator commentValidator;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Comment saveComment(Comment comment) {
        commentValidator.validateInputComment(comment);

        com.intuit.commentmanager.entity.Comment savedComment = null;
        if(comment.getId() > 0) {
            savedComment = commentRepository.findById(comment.getId()).get();
            savedComment.setContent(comment.getContent());
        } else {
            savedComment = commentMapper.mapInboundToEntity(comment);
            Post post = postRepository.findById(comment.getPostId()).get();
            savedComment.setPost(post);
            Profile profile = profileRepository.findById(comment.getProfileId()).get();
            savedComment.setCommentedBy(profile);
            if(comment.getParentCommentId() > 0) {
                com.intuit.commentmanager.entity.Comment parentComment =
                        commentRepository.findById(comment.getParentCommentId()).get();
                savedComment.setParentComment(parentComment);
            }
        }
        com.intuit.commentmanager.entity.Comment latestComment = commentRepository.save(savedComment);
        return commentMapper.mapEntityToInbound(latestComment);
    }

    @Override
    public List<Comment> getComments() {
        List<com.intuit.commentmanager.entity.Comment> commentList = commentRepository.findAll();
        return commentList.parallelStream()
                .map(comment -> commentMapper.mapEntityToInbound(comment)).collect(Collectors.toList());
    }

    @Override
    public Comment getComment() {
        return null;
    }
}
