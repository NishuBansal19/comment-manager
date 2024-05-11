package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.dto.inbound.Post;
import com.intuit.commentmanager.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
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
