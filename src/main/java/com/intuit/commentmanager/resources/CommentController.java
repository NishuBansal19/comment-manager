package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.dto.inbound.Comment;
import com.intuit.commentmanager.service.CommentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> commentList = commentService.getComments();
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<Comment>> getCommentsByPostId(@PathVariable long postId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(name = "limit", defaultValue = "2") int limit) {
        Page<Comment> commentList = commentService.getCommentsByPostId(postId,
                PageRequest.of(page, limit, Sort.Direction.DESC, "createdDt"));
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long id) {
        Comment comment = commentService.getComment(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/replies/{parentCommentId}")
    public ResponseEntity<Page<Comment>> getRepliesByParentCommentId(@PathVariable long parentCommentId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(name = "limit", defaultValue = "2") int limit) {
        Page<Comment> commentList = commentService.getRepliesByParentCommentId(parentCommentId,
                PageRequest.of(page, limit, Sort.Direction.DESC, "createdDt"));
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable long id) {
        commentService.deleteCommentById(id);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.NO_CONTENT);
    }
}
