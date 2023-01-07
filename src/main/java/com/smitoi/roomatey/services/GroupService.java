package com.smitoi.roomatey.services;

import com.smitoi.roomatey.dto.requests.group.CreateGroupRequest;
import com.smitoi.roomatey.dto.requests.group.UpdateGroupRequest;
import com.smitoi.roomatey.entity.Group;
import com.smitoi.roomatey.entity.Invitation;
import com.smitoi.roomatey.entity.User;
import com.smitoi.roomatey.entity.UserGroup;
import com.smitoi.roomatey.entity.keys.UserGroupKey;
import com.smitoi.roomatey.exceptions.NotOwnerOfGroup;
import com.smitoi.roomatey.repository.GroupRepository;
import com.smitoi.roomatey.repository.InvitationRepository;
import com.smitoi.roomatey.repository.UserGroupRepository;
import com.smitoi.roomatey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

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

    public Group addGroup(CreateGroupRequest request, User user) {
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

        return group;
    }

    public Group editGroup(UpdateGroupRequest request, Long id, User user) {
        Group group = groupRepository.findById(id).orElseThrow();

        UserGroup membership = userGroupRepository.findByGroupAndUser(group, user).orElseThrow();

        if (!Objects.equals(membership.getRole(), UserGroup.ROLE_OWNER)) {
            throw new NotOwnerOfGroup();
        }

        group.setName(request.getName());
        group.setDescription(request.getDescription());

        return groupRepository.save(group);
    }

    public Invitation inviteGuestToGroup(Long groupId, String email, User user) {
        Group group = groupRepository.findById(groupId).orElseThrow();

        Invitation invitation = Invitation.builder()
                .group(group)
                .email(email)
                .status(Invitation.STATUS_SENT)
                .sender(user)
                .uuid(UUID.randomUUID())
                .build();

        return invitationRepository.save(invitation);
    }

    public UserGroup inviteUserToGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        UserGroup membership = UserGroup.builder()
                .id(UserGroupKey.builder().userId(user.getId()).groupId(group.getId()).build())
                .role(UserGroup.ROLE_GUEST)
                .build();

        return userGroupRepository.save(membership);
    }

}
