package com.smitoi.roomatey.mapper;

import com.smitoi.roomatey.dto.responses.NoteDto;
import com.smitoi.roomatey.entity.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteDto mapToDto(Note note) {
        return NoteDto.builder()
                .id(note.getId())
                .creator(note.getCreator().getName())
                .content(note.getContent())
                .build();
    }
}
