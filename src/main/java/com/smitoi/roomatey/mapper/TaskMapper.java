package com.smitoi.roomatey.mapper;

import com.smitoi.roomatey.dto.responses.TaskDto;
import com.smitoi.roomatey.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto mapToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .status(task.getStatus())
                .description(task.getDescription())
                .category(task.getCategory())
                .assignee(task.getAssignee().getName())
                .creator(task.getCreator().getName())
                .build();
    }

}
