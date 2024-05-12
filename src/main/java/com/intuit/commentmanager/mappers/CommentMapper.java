package com.intuit.commentmanager.mappers;

import com.intuit.commentmanager.entity.Comment;
import com.intuit.commentmanager.repository.CommentRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", imports = {Date.class, Optional.class})
public abstract class CommentMapper {

    @Autowired
    CommentRepository commentRepository;

    @Mapping(target = "createdDt", expression = "java(new Date())")
    public abstract Comment mapInboundToEntity(com.intuit.commentmanager.dto.inbound.Comment comment);

    @Mapping(target = "createdDate", source = "createdDt")
    @Mapping(target = "modifiedDate", source = "updatedDt")
    @Mapping(target = "profileId", expression = "java(Optional.ofNullable(comment.getCommentedBy()).map(p -> p.getId()).orElse(0l))")
    @Mapping(target = "postId", expression = "java(Optional.ofNullable(comment.getPost()).map(p -> p.getId()).orElse(0l))")
    @Mapping(target = "parentCommentId", expression = "java(Optional.ofNullable(comment.getParentComment()).map(p -> p.getId()).orElse(0l))")
    @BeanMapping(qualifiedByName = "setChildCommentFlag")
    public abstract com.intuit.commentmanager.dto.inbound.Comment mapEntityToInbound(Comment comment);

    @AfterMapping
    @Named("setChildCommentFlag")
    protected void setChildCommentFlag(@MappingTarget com.intuit.commentmanager.dto.inbound.Comment commentResponse, Comment comment) {
        List<Comment> childComments = commentRepository.findAnyRowWithIdAsParentId(comment.getId(),
                PageRequest.of(0, 1));
        boolean hasChildComments = !CollectionUtils.isEmpty(childComments);
        commentResponse.setChildCommentPresent(hasChildComments);
    }
}
