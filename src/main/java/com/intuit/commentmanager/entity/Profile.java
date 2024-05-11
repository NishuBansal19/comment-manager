package com.intuit.commentmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "profile")
@EntityListeners(AuditEntity.class)
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;

    @CreatedDate
    @Column(name = "created_dt")
    private Date createdDt;

    @LastModifiedDate
    @Column(name = "updated_dt")
    private Date updatedDt;

}
