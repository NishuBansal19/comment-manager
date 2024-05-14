package com.intuit.commentmanager.service;

import com.intuit.commentmanager.dto.inbound.Post;
import com.intuit.commentmanager.entity.Profile;
import com.intuit.commentmanager.exceptions.InvalidInputException;
import com.intuit.commentmanager.mappers.PostMapper;
import com.intuit.commentmanager.repository.PostRepository;
import com.intuit.commentmanager.repository.ProfileRepository;
import com.intuit.commentmanager.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private PostMapper postMapper;

    @Test
    void test_savePost_error() {
        Mockito.when(profileRepository.findById(1L)).thenThrow(InvalidInputException.class);
        com.intuit.commentmanager.dto.inbound.Post post =
                new Post();
        post.setId(1L);
        Assertions.assertThrows(
                InvalidInputException.class, () -> postService.savePost(post));
    }

    @Test
    void test_savePost_success() {
        Profile postedBy = new Profile();
        postedBy.setId(1L);
        Mockito.when(profileRepository.findById(1L)).thenReturn(Optional.of(postedBy));
        com.intuit.commentmanager.dto.inbound.Post post = new Post();
        post.setId(1L);
        post.setContent("Test Comment");
        post.setProfileId(1L);
        com.intuit.commentmanager.entity.Post savedPost = new com.intuit.commentmanager.entity.Post();
        savedPost.setId(1L);
        savedPost.setPostedBy(postedBy);
        savedPost.setContent("Test Comment");
        Mockito.when(postRepository.save(Mockito.any())).thenReturn(savedPost);
        Mockito.when(postMapper.mapInboundDtoToPost(post)).thenReturn(savedPost);
        Mockito.when(postMapper.mapEntityToInbound(savedPost)).thenReturn(post);
        Post result = postService.savePost(post);
        Assertions.assertEquals(result, post);
    }

    @Test
    void test_deletePost() {
        Mockito.doNothing().when(postRepository).deleteById(Mockito.any());
        postService.deletePostById(1L);
    }
}
