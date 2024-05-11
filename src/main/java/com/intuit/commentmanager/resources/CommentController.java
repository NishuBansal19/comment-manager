package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.dto.inbound.Comment;
import com.intuit.commentmanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> commentList = commentService.getComments();
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }
}
