package com.intuit.commentmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {
    @CreatedDate
    @Column(name = "created_dt")
    private Date createdDt;

    @LastModifiedDate
    @Column(name = "updated_dt")
    private Date updatedDt;
}
