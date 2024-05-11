package com.intuit.commentmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "posted_by", nullable = false)
    private Profile postedBy;

    @CreatedDate
    @Column(name = "created_dt")
    private Date createdDt;

    @LastModifiedDate
    @Column(name = "updated_dt")
    private Date updatedDt;
}
