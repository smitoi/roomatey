package com.smitoi.roomatey.dto.requests.task;

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
public class CreateTaskRequest {

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
    private Long assigneeId;
}
