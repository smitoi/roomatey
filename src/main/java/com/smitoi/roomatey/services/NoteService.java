package com.smitoi.roomatey.services;

import com.smitoi.roomatey.dto.requests.note.CreateNoteRequest;
import com.smitoi.roomatey.dto.requests.note.UpdateNoteRequest;
import com.smitoi.roomatey.dto.responses.NoteDto;
import com.smitoi.roomatey.entity.Group;
import com.smitoi.roomatey.entity.Note;
import com.smitoi.roomatey.entity.User;
import com.smitoi.roomatey.entity.UserGroup;
import com.smitoi.roomatey.exceptions.NotMemberOfGroup;
import com.smitoi.roomatey.exceptions.NotOwnerOfObject;
import com.smitoi.roomatey.mapper.NoteMapper;
import com.smitoi.roomatey.repository.GroupRepository;
import com.smitoi.roomatey.repository.NoteRepository;
import com.smitoi.roomatey.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    @Autowired
    private final NoteRepository noteRepository;

    @Autowired
    private final GroupRepository groupRepository;

    @Autowired
    private final UserGroupRepository userGroupRepository;

    @Autowired
    private final NoteMapper noteMapper;


    public List<NoteDto> getNotesForGroup(Long groupId, String search, User user) {
        Group group = groupRepository.getReferenceById(groupId);
        Optional<UserGroup> userGroup = userGroupRepository.findByGroupAndUser(group, user);

        if (userGroup.isEmpty() || Objects.equals(userGroup.get().getRole(), UserGroup.ROLE_GUEST)) {
            throw new NotMemberOfGroup();
        }

        return group.getNotes().stream()
                .map(noteMapper::mapToDto).collect(Collectors.toList());

    }

    public NoteDto addNote(CreateNoteRequest request, Long groupId, User user) {
        Group group = groupRepository.getReferenceById(groupId);

        Note note = Note.builder()
                .content(request.getContent())
                .creator(user)
                .group(group)
                .build();

        return noteMapper.mapToDto(noteRepository.save(note));
    }

    public NoteDto editNote(UpdateNoteRequest request, Long id, User user) {
        Note note = noteRepository.findById(id).orElseThrow();

        if (!note.isOwnedBy(user)) {
            throw new NotOwnerOfObject(Note.class);
        }

        note.setContent(request.getContent());

        return noteMapper.mapToDto(noteRepository.save(note));
    }

}
