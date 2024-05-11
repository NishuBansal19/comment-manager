package com.intuit.commentmanager.mappers;

import com.intuit.commentmanager.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;
import java.util.Optional;

@Mapper(componentModel = "spring", imports = {Date.class, Optional.class})
public interface CommentMapper {

    @Mapping(target = "createdDt", expression = "java(new Date())")
    public Comment mapInboundToEntity(com.intuit.commentmanager.dto.inbound.Comment comment);

    @Mapping(target = "createdDate", source = "createdDt")
    @Mapping(target = "modifiedDate", source = "updatedDt")
    @Mapping(target = "profileId", expression = "java(Optional.ofNullable(comment.getCommentedBy()).map(p -> p.getId()).orElse(0l))")
    @Mapping(target = "postId", expression = "java(Optional.ofNullable(comment.getPost()).map(p -> p.getId()).orElse(0l))")
    @Mapping(target = "parentCommentId", expression = "java(Optional.ofNullable(comment.getParentComment()).map(p -> p.getId()).orElse(0l))")
    public com.intuit.commentmanager.dto.inbound.Comment mapEntityToInbound(Comment comment);


}
