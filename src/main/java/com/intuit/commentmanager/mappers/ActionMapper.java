package com.intuit.commentmanager.mappers;

import com.intuit.commentmanager.dto.inbound.ActionInput;
import com.intuit.commentmanager.entity.ViewerAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;
import java.util.Optional;

@Mapper(componentModel = "spring", imports = {Date.class, Optional.class})
public interface ActionMapper {

    @Mapping(target = "createdDt", expression = "java(new Date())")
    public ViewerAction mapInboundToEntity(ActionInput actionInput);

    @Mapping(target = "createdDate", source = "createdDt")
    @Mapping(target = "modifiedDate", source = "updatedDt")
    @Mapping(target = "profileId", expression = "java(Optional.ofNullable(viewerAction.getActionBy()).map(p -> p.getId()).orElse(0l))")
    @Mapping(target = "commentId", expression = "java(Optional.ofNullable(viewerAction.getComment()).map(c -> c.getId()).orElse(0l))")
    public ActionInput mapEntityToInbound(ViewerAction viewerAction);
}
