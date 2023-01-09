package com.smitoi.roomatey.services;

import com.smitoi.roomatey.dto.requests.note.CreateNoteRequest;
import com.smitoi.roomatey.dto.requests.note.UpdateNoteRequest;
import com.smitoi.roomatey.dto.requests.task.CreateTaskRequest;
import com.smitoi.roomatey.dto.requests.task.UpdateTaskRequest;
import com.smitoi.roomatey.dto.responses.NoteDto;
import com.smitoi.roomatey.dto.responses.TaskDto;
import com.smitoi.roomatey.entity.*;
import com.smitoi.roomatey.exceptions.NotMemberOfGroup;
import com.smitoi.roomatey.exceptions.NotOwnerOfObject;
import com.smitoi.roomatey.mapper.TaskMapper;
import com.smitoi.roomatey.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private final GroupRepository groupRepository;

    @Autowired
    private final UserGroupRepository userGroupRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final TaskMapper taskMapper;

    public List<TaskDto> getTasksForGroup(Long groupId, String search, User user) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        Optional<UserGroup> userGroup = userGroupRepository.findByGroupAndUser(group, user);

        if (userGroup.isEmpty() || Objects.equals(userGroup.get().getRole(), UserGroup.ROLE_GUEST)) {
            throw new NotMemberOfGroup();
        }

        return group.getTasks().stream()
                .map(taskMapper::mapToDto).collect(Collectors.toList());

    }

    public TaskDto addTask(CreateTaskRequest request, Long groupId, User user) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        User assignee = userRepository.findById(request.getAssigneeId()).orElseThrow();

        Task task = Task.builder()
                .description(request.getDescription())
                .status(Task.STATUS_PENDING)
                .category(request.getCategory())
                .creator(user)
                .assignee(assignee)
                .group(group)
                .build();

        return taskMapper.mapToDto(taskRepository.save(task));
    }

    public TaskDto editTask(UpdateTaskRequest request, Long id, User user) {
        Task task = taskRepository.findById(id).orElseThrow();
        User assignee = userRepository.findById(request.getAssigneeId()).orElseThrow();

        if (!task.isOwnedBy(user)) {
            throw new NotOwnerOfObject(Note.class);
        }

        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setAssignee(assignee);

        return taskMapper.mapToDto(taskRepository.save(task));
    }
}
