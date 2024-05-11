package com.intuit.commentmanager.mappers;

import com.intuit.commentmanager.entity.Post;
import com.intuit.commentmanager.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;
import java.util.Optional;

@Mapper(componentModel = "spring", imports = {Date.class, Optional.class, Profile.class})
public interface PostMapper {

    @Mapping(target = "createdDt", expression = "java(new Date())")
    public Post mapInboundDtoToPost(com.intuit.commentmanager.dto.inbound.Post post);

    @Mapping(target = "createdDate", source = "createdDt")
    @Mapping(target = "modifiedDate", source = "updatedDt")
    @Mapping(target = "profileId", expression = "java(Optional.ofNullable(post.getPostedBy()).map(p -> p.getId()).orElse(0l))")
    public com.intuit.commentmanager.dto.inbound.Post mapEntityToInbound(Post post);

}
