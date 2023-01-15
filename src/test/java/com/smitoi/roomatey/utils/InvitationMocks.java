package com.smitoi.roomatey.utils;

import com.smitoi.roomatey.dto.responses.InvitationDto;
import com.smitoi.roomatey.entity.Invitation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InvitationMocks {

    public static Invitation mockInvitation() {
        return Invitation.builder()
                .email("invited@roomatey.com")
                .build();
    }

    public static InvitationDto mockInvitationDto() {
        return InvitationDto.builder()
                .to(mockInvitation().getEmail())
                .build();
    }
}
