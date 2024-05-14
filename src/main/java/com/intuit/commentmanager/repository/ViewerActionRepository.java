package com.intuit.commentmanager.repository;

import com.intuit.commentmanager.entity.Comment;
import com.intuit.commentmanager.entity.Profile;
import com.intuit.commentmanager.entity.ViewerAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewerActionRepository extends JpaRepository<ViewerAction, Long> {

    List<ViewerAction> findByCommentAndActionBy(Comment comment, Profile profile);

    @Query(value = "SELECT va FROM ViewerAction va where va.comment.id =?1 AND va.actionType=?2")
    Page<ViewerAction> findReplyCommentByParentCommentId(long commentId, String actionType, Pageable pageable);

    @Query(value = "SELECT va FROM ViewerAction va where va.comment.id =?1 AND va.actionType=?2")
    List<ViewerAction> findReplyCommentByParentCommentId(long commentId, String actionType);

}
