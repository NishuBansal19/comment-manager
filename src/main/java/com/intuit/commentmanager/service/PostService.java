package com.intuit.commentmanager.service;

import com.intuit.commentmanager.dto.inbound.Post;

import java.util.List;

public interface PostService {

    public com.intuit.commentmanager.entity.Post savePost(Post post);

    public List<com.intuit.commentmanager.entity.Post> getPosts();

    public com.intuit.commentmanager.entity.Post getPost(long id);
}
