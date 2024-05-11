package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.entity.Post;
import com.intuit.commentmanager.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<Post> saveProfile(@RequestBody com.intuit.commentmanager.dto.inbound.Post post) {
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.OK);
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable long id) {
        return postService.getPost(id);
    }

}
