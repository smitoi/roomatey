package com.smitoi.roomatey.utils;

import com.smitoi.roomatey.dto.requests.auth.RegisterRequest;
import com.smitoi.roomatey.dto.requests.group.CreateGroupRequest;
import com.smitoi.roomatey.dto.requests.group.UpdateGroupRequest;
import com.smitoi.roomatey.dto.responses.GroupDto;
import com.smitoi.roomatey.entity.Group;
import com.smitoi.roomatey.entity.User;
import com.smitoi.roomatey.entity.UserGroup;
import com.smitoi.roomatey.entity.keys.UserGroupKey;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class GroupMocks {

    public static Group mockGroup() {
        return Group.builder()
                .id(1L)
                .name("Test Group")
                .description("This is a mock group")
                .build();
    }

    public static CreateGroupRequest mockCreateGroupRequest() {
        Group group = GroupMocks.mockGroup();

        return CreateGroupRequest.builder()
                .name(group.getName())
                .description(group.getDescription())
                .build();
    }

    public static UpdateGroupRequest mockUpdateGroupRequest() {
        Group group = GroupMocks.mockGroup();

        return UpdateGroupRequest.builder()
                .name("Updated " + group.getName())
                .description(group.getDescription() + " that has been updated")
                .build();
    }

    public static GroupDto mockGroupDto() {
        Group group = GroupMocks.mockGroup();

        return GroupDto.builder()
                .id(1L)
                .name(group.getName())
                .description(group.getDescription())
                .build();
    }
}
