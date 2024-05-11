package com.intuit.commentmanager.repository;

import com.intuit.commentmanager.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findTop10ByOrderByCreatedDtDesc();
}
