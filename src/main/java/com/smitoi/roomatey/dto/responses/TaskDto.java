package com.smitoi.roomatey.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    public Long id;

    public String description;

    public String category;

    public String status;

    public String assignee;

    public String creator;
}
