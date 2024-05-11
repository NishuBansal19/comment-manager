package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.entity.Comment;
import com.intuit.commentmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @PostMapping
    public ResponseEntity<Comment> postComment(@RequestBody Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> comments = commentRepository.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
