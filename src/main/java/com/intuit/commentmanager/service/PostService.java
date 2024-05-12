package com.intuit.commentmanager.service;

import com.intuit.commentmanager.dto.inbound.Post;

import java.util.List;

public interface PostService {

    public Post savePost(Post post);

    public List<Post> getPosts();

    public Post getPost(long id);

    public void deletePostById(long id);
}
