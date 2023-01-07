package com.smitoi.roomatey.dto.requests.group;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGroupRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}