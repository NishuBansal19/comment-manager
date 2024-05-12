package com.intuit.commentmanager.mappers;

import com.intuit.commentmanager.dto.outbound.BasicProfileDetails;
import com.intuit.commentmanager.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    public BasicProfileDetails mapProfileToBasicProfile(Profile profile);
}
