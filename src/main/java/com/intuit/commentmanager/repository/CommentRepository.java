package com.intuit.commentmanager.repository;

import com.intuit.commentmanager.entity.Comment;
import com.intuit.commentmanager.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT ct FROM Comment ct where ct.post.id = ?1 AND ct.parentComment IS NULL")
    Page<Comment> findParentCommentByPostId(long postId, Pageable pageable);

    @Query(value = "SELECT ct FROM Comment ct where ct.parentComment.id =?1")
    Page<Comment> findReplyCommentByParentCommentId(long parentCommentId, Pageable pageable);

    @Query(value = "SELECT ct FROM Comment ct where ct.parentComment.id =?1")
    List<Comment> findAnyRowWithIdAsParentId(long id, Pageable pageable);

    List<Comment> findByPostId(Post post, Pageable pageable);
}
