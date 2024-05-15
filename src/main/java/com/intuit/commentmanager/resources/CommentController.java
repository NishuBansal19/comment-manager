package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.dto.inbound.Comment;
import com.intuit.commentmanager.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Operation(summary = "Api to put a comment on post and to reply on a comment")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Utility Api to list all the comments")
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> commentList = commentService.getComments();
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "Api to get paginated parent comments by post id")
    public ResponseEntity<Page<Comment>> getCommentsByPostId(@PathVariable long postId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(name = "limit", defaultValue = "2") int limit) {
        Page<Comment> commentList = commentService.getCommentsByPostId(postId,
                PageRequest.of(page, limit, Sort.Direction.DESC, "createdDt"));
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Utility Api to get comment by id")
    public ResponseEntity<Comment> getCommentById(@PathVariable long id) {
        Comment comment = commentService.getComment(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/replies/{parentCommentId}")
    @Operation(summary = "Api to get paginated replies on a comment by parent comment id")
    public ResponseEntity<Page<Comment>> getRepliesByParentCommentId(@PathVariable long parentCommentId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(name = "limit", defaultValue = "2") int limit) {
        Page<Comment> commentList = commentService.getRepliesByParentCommentId(parentCommentId,
                PageRequest.of(page, limit, Sort.Direction.DESC, "createdDt"));
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Api to delete the parent comment and corresponding child comments")
    public ResponseEntity<String> deleteCommentById(@PathVariable long id) {
        commentService.deleteCommentById(id);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.NO_CONTENT);
    }
}
