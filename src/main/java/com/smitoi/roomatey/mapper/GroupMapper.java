package com.smitoi.roomatey.mapper;

import com.smitoi.roomatey.dto.responses.GroupDto;
import com.smitoi.roomatey.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public GroupDto mapToDto(Group group) {
        return GroupDto.builder()
                .name(group.getName())
                .description(group.getDescription())
                .id(group.getId())
                .build();
    }
}
