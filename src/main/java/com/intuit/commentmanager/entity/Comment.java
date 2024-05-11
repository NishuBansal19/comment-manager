package com.intuit.commentmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
}
