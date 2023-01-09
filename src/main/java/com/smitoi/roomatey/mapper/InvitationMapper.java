package com.smitoi.roomatey.mapper;

import com.smitoi.roomatey.dto.responses.InvitationDto;
import com.smitoi.roomatey.entity.Invitation;
import org.springframework.stereotype.Component;

@Component
public class InvitationMapper {

    public InvitationDto mapToDto(Invitation invitation) {
        return InvitationDto.builder()
                .from(invitation.getSender().getName())
                .to(invitation.getEmail())
                .status(invitation.getStatus())
                .build();
    }
}