package com.intuit.commentmanager.dto.inbound;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

    private long id;
    private String content;
    private long profileId;

    private Date createdDate;
    private Date modifiedDate;

    private boolean comments;
}
