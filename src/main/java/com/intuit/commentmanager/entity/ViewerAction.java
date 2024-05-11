package com.intuit.commentmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "viewer_action")
public class ViewerAction extends AuditEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String action_type;

    @ManyToOne
    @JoinColumn(name = "action_by", nullable = false)
    private Profile postedBy;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment commentId;
}
