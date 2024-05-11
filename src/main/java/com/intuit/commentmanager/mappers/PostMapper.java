package com.intuit.commentmanager.mappers;

import com.intuit.commentmanager.entity.Post;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;

@Mapper(componentModel = "spring", imports = {Date.class})
public interface PostMapper {

    @Mapping(target = "createdDt", expression = "java(new Date())")
    public Post mapInboundDtoToPost(com.intuit.commentmanager.dto.inbound.Post post);
}
