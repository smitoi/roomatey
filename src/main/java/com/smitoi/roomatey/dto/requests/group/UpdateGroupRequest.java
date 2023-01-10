package com.smitoi.roomatey.dto.requests.group;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGroupRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 5, max = 65)
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 15, max = 255)
    private String description;
}