package com.intuit.commentmanager.resources;

import com.intuit.commentmanager.dto.inbound.Post;
import com.intuit.commentmanager.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Api to create a post")
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Utility api to list all the posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Api to get the post by id")
    public Post getPostById(@PathVariable long id) {
        return postService.getPost(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Api to delete the post and all linked comments by id")
    public ResponseEntity<String> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }

}
