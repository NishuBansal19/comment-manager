package com.intuit.commentmanager.repository;

import com.intuit.commentmanager.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query(value = "SELECT c FROM Comment ct where ct.post.id = ?1 AND ct.parentComment IS NULL")
//    List<Comment> findParentCommentByPostId(long postId);
}
