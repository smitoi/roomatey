package com.smitoi.roomatey.dto.requests.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {

    @NotBlank
    @NotEmpty
    private String description;

    @NotBlank
    @NotEmpty
    private String category;

    @NotBlank
    @NotEmpty
    private String status;

    @NotNull
    private Long assigneeId;
}
