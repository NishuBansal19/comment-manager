package com.intuit.commentmanager.service.impl;

import com.intuit.commentmanager.dto.inbound.Post;
import com.intuit.commentmanager.entity.Profile;
import com.intuit.commentmanager.exceptions.InvalidInputException;
import com.intuit.commentmanager.mappers.PostMapper;
import com.intuit.commentmanager.repository.PostRepository;
import com.intuit.commentmanager.repository.ProfileRepository;
import com.intuit.commentmanager.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public com.intuit.commentmanager.entity.Post savePost(Post post) {
        Profile postedBy = profileRepository.findById(post.getProfileId())
                .orElseThrow(() -> new InvalidInputException("No profile found with given posted by id"));
        com.intuit.commentmanager.entity.Post postEntity = postMapper.mapInboundDtoToPost(post);
        postEntity.setPostedBy(postedBy);
        com.intuit.commentmanager.entity.Post savedPost = postRepository.save(postEntity);
        return savedPost;
    }

    @Override
    public List<com.intuit.commentmanager.entity.Post> getPosts() {
        return postRepository.findTop10ByOrderByCreatedDtDesc();
    }

    @Override
    public com.intuit.commentmanager.entity.Post getPost(long id) {
        Optional<com.intuit.commentmanager.entity.Post> post = postRepository.findById(id);
        if(post.isEmpty()) {
            throw new InvalidInputException("No post found with the given id");
        }
        return post.get();
    }
}
