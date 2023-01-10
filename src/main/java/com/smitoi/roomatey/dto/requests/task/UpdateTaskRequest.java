package com.smitoi.roomatey.dto.requests.task;

import com.smitoi.roomatey.validator.ValidTaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 5, max = 65)
    private String description;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 5, max = 65)
    private String category;

    @NotNull
    @NotBlank
    @NotEmpty
    @ValidTaskStatus
    private String status;

    @NotNull
    private Long assigneeId;
}
