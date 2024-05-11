package com.intuit.commentmanager.repository;

import com.intuit.commentmanager.entity.ViewerAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewerActionRepository extends JpaRepository<ViewerAction, Long> {
}
