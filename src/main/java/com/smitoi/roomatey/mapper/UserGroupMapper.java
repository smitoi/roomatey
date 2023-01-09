package com.smitoi.roomatey.mapper;


import com.smitoi.roomatey.dto.responses.UserGroupDto;
import com.smitoi.roomatey.entity.UserGroup;
import org.springframework.stereotype.Component;


@Component
public class UserGroupMapper {

    public UserGroupDto mapToDto(UserGroup userGroup) {
        return UserGroupDto.builder()
                .groupId(userGroup.getId().getGroupId())
                .groupName(userGroup.getGroup().getName())
                .role(userGroup.getRole())
                .userId(userGroup.getUser().getName())
                .build();
    }
}
