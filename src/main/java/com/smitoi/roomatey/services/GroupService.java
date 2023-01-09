package com.smitoi.roomatey.services;

import com.smitoi.roomatey.dto.requests.group.CreateGroupRequest;
import com.smitoi.roomatey.dto.requests.group.UpdateGroupRequest;
import com.smitoi.roomatey.dto.responses.GroupDto;
import com.smitoi.roomatey.dto.responses.InvitationDto;
import com.smitoi.roomatey.dto.responses.UserGroupDto;
import com.smitoi.roomatey.entity.Group;
import com.smitoi.roomatey.entity.Invitation;
import com.smitoi.roomatey.entity.User;
import com.smitoi.roomatey.entity.UserGroup;
import com.smitoi.roomatey.entity.keys.UserGroupKey;
import com.smitoi.roomatey.exceptions.NotOwnerOfObject;
import com.smitoi.roomatey.mapper.GroupMapper;
import com.smitoi.roomatey.mapper.InvitationMapper;
import com.smitoi.roomatey.mapper.UserGroupMapper;
import com.smitoi.roomatey.repository.GroupRepository;
import com.smitoi.roomatey.repository.InvitationRepository;
import com.smitoi.roomatey.repository.UserGroupRepository;
import com.smitoi.roomatey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    @Autowired
    private final GroupRepository groupRepository;

    @Autowired
    private final UserGroupRepository userGroupRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final InvitationRepository invitationRepository;

    @Autowired
    private final UserGroupMapper userGroupMapper;

    @Autowired
    private final GroupMapper groupMapper;

    @Autowired
    private final InvitationMapper invitationMapper;

    public GroupDto addGroup(CreateGroupRequest request, User user) {
        Group group = Group.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        group = groupRepository.save(group);

        UserGroup membership = UserGroup.builder()
                .id(UserGroupKey.builder().userId(user.getId()).groupId(group.getId()).build())
                .role(UserGroup.ROLE_OWNER)
                .build();

        userGroupRepository.save(membership);

        return groupMapper.mapToDto(group);
    }

    public GroupDto editGroup(UpdateGroupRequest request, Long id, User user) {
        Group group = groupRepository.findById(id).orElseThrow();

        if (!group.isOwnedBy(user)) {
            throw new NotOwnerOfObject(Group.class);
        }

        group.setName(request.getName());
        group.setDescription(request.getDescription());

        return groupMapper.mapToDto(groupRepository.save(group));
    }

    public InvitationDto inviteGuestToGroup(Long groupId, String email, User user) {
        Group group = groupRepository.findById(groupId).orElseThrow();

        Invitation invitation = Invitation.builder()
                .group(group)
                .email(email)
                .status(Invitation.STATUS_SENT)
                .sender(user)
                .uuid(UUID.randomUUID())
                .build();

        return invitationMapper.mapToDto(invitationRepository.save(invitation));
    }

    public UserGroupDto inviteUserToGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        UserGroup membership = UserGroup.builder()
                .id(UserGroupKey.builder().userId(user.getId()).groupId(group.getId()).build())
                .role(UserGroup.ROLE_GUEST)
                .build();

        return userGroupMapper.mapToDto(userGroupRepository.save(membership));
    }

    public List<UserGroupDto> getGroupsForUser(User user) {

        return userGroupRepository.findAllByUser(user).stream()
                .map(userGroupMapper::mapToDto).collect(Collectors.toList());
    }

}
