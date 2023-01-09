package com.smitoi.roomatey.controller;

import com.smitoi.roomatey.dto.requests.note.CreateNoteRequest;
import com.smitoi.roomatey.dto.requests.note.UpdateNoteRequest;
import com.smitoi.roomatey.dto.responses.NoteDto;
import com.smitoi.roomatey.entity.Note;
import com.smitoi.roomatey.services.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController extends BaseController {

    @Autowired
    private final NoteService service;

    @GetMapping("/{groupId}")
    public ResponseEntity<List<NoteDto>> index(@PathVariable Long groupId, @RequestParam String search) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getNotesForGroup(groupId, search, getLoggedInUser()));
    }

    @PostMapping("/{groupId}")
    public ResponseEntity<NoteDto> store(@PathVariable Long groupId, @Valid @RequestBody CreateNoteRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addNote(request, groupId, getLoggedInUser()));
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDto> update(@PathVariable Long noteId, @Valid @RequestBody UpdateNoteRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.editNote(request, noteId, getLoggedInUser()));
    }
}
