package com.smitoi.roomatey.services;

import com.smitoi.roomatey.dto.requests.auth.LoginRequest;
import com.smitoi.roomatey.dto.requests.auth.RegisterRequest;
import com.smitoi.roomatey.dto.requests.group.CreateGroupRequest;
import com.smitoi.roomatey.dto.requests.group.UpdateGroupRequest;
import com.smitoi.roomatey.dto.responses.AuthenticationResponse;
import com.smitoi.roomatey.dto.responses.GroupDto;
import com.smitoi.roomatey.dto.responses.InvitationDto;
import com.smitoi.roomatey.entity.Group;
import com.smitoi.roomatey.entity.Invitation;
import com.smitoi.roomatey.entity.User;
import com.smitoi.roomatey.mapper.GroupMapper;
import com.smitoi.roomatey.mapper.InvitationMapper;
import com.smitoi.roomatey.mapper.UserGroupMapper;
import com.smitoi.roomatey.repository.GroupRepository;
import com.smitoi.roomatey.repository.InvitationRepository;
import com.smitoi.roomatey.repository.UserGroupRepository;
import com.smitoi.roomatey.repository.UserRepository;
import com.smitoi.roomatey.utils.GroupMocks;
import com.smitoi.roomatey.utils.InvitationMocks;
import com.smitoi.roomatey.utils.UserMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("testing")
public class GroupServiceTest {

    @InjectMocks
    private GroupService groupService;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserGroupRepository userGroupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private InvitationRepository invitationRepository;

    @Mock
    private GroupMapper groupMapper;

    @Mock
    private InvitationMapper invitationMapper;

    @Test
    public void addGroupTest() {
        Group group = GroupMocks.mockGroup();
        GroupDto groupDto = GroupMocks.mockGroupDto();

        when(groupRepository.save(group)).thenReturn(group);
        when(groupMapper.mapToDto(group)).thenReturn(groupDto);

        GroupDto result = groupService.addGroup(GroupMocks.mockCreateGroupRequest(), UserMocks.mockUser());

        assertEquals(result.getName(), group.getName());
        assertEquals(result.getDescription(), group.getDescription());
    }

    @Test
    public void editGroupTest() {

        Group group = GroupMocks.mockGroup();
        Group updatedGroup = GroupMocks.mockGroup();

        GroupDto groupDto = GroupMocks.mockGroupDto();
        UpdateGroupRequest updateGroupRequest = GroupMocks.mockUpdateGroupRequest();

        updatedGroup.setName(updateGroupRequest.getName());
        updatedGroup.setDescription(updateGroupRequest.getDescription());

        groupDto.setName(updatedGroup.getName());
        groupDto.setDescription(updatedGroup.getDescription());

        when(groupRepository.findById(1L)).thenReturn(Optional.ofNullable(group));
        when(groupRepository.save(group)).thenReturn(updatedGroup);
        when(groupMapper.mapToDto(group)).thenReturn(groupDto);

        GroupDto result = groupService.editGroup(updateGroupRequest, 1L, UserMocks.mockUser());

        assertEquals(result.getName(), updateGroupRequest.getName());
        assertEquals(result.getDescription(), updateGroupRequest.getDescription());
    }

    @Test
    public void inviteGuestToGroupTest() {
        Group group = GroupMocks.mockGroup();
        Invitation invitation = InvitationMocks.mockInvitation();

        when(groupRepository.findById(1L)).thenReturn(Optional.ofNullable(group));
        when(invitationRepository.save(any())).thenReturn(invitation);
        when(invitationMapper.mapToDto(invitation)).thenReturn(InvitationMocks.mockInvitationDto());

        InvitationDto result = groupService.inviteGuestToGroup(group.getId(), invitation.getEmail(), UserMocks.mockUser());

        assertEquals(result.getTo(), invitation.getEmail());
    }
}