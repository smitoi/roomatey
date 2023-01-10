package com.smitoi.roomatey.controller;

import com.smitoi.roomatey.dto.requests.group.CreateGroupRequest;
import com.smitoi.roomatey.dto.requests.group.InviteGuestToGroupRequest;
import com.smitoi.roomatey.dto.requests.group.InviteUserToGroupRequest;
import com.smitoi.roomatey.dto.requests.group.UpdateGroupRequest;
import com.smitoi.roomatey.dto.responses.GroupDto;
import com.smitoi.roomatey.dto.responses.InvitationDto;
import com.smitoi.roomatey.dto.responses.UserGroupDto;
import com.smitoi.roomatey.entity.Group;
import com.smitoi.roomatey.entity.Invitation;
import com.smitoi.roomatey.entity.UserGroup;
import com.smitoi.roomatey.services.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
@Tag(name = "Groups", description = "Groups related endpoints.")
public class GroupController extends BaseController {
    @Autowired
    private final GroupService service;

    @GetMapping("/")
    @Operation(summary = "Index", description = "View all groups that you are part of.")
    public ResponseEntity<List<UserGroupDto>> index() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getGroupsForUser(getLoggedInUser()));
    }

    @PostMapping("/")
    @Operation(summary = "Store", description = "Create a new group.")
    public ResponseEntity<GroupDto> store(@Valid @RequestBody CreateGroupRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addGroup(request, getLoggedInUser()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "Update an existing group.")
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @Valid @RequestBody UpdateGroupRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.editGroup(request, id, getLoggedInUser()));
    }

    @PostMapping("/{id}/invite-user")
    @Operation(summary = "InviteUser", description = "Invite a registered user to your group.")
    public ResponseEntity<UserGroupDto> inviteUser(@PathVariable Long id, @Valid @RequestBody InviteUserToGroupRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.inviteUserToGroup(id, request.getUserId()));
    }

    @PostMapping("/{id}/invite-guest")
    @Operation(summary = "InviteGuest", description = "Invite a guest to your group.")
    public ResponseEntity<InvitationDto> inviteGuest(@PathVariable Long id, @Valid @RequestBody InviteGuestToGroupRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.inviteGuestToGroup(id, request.getEmail(), getLoggedInUser()));
    }
}
