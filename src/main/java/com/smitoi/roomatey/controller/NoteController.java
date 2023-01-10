package com.smitoi.roomatey.controller;

import com.smitoi.roomatey.dto.requests.note.CreateNoteRequest;
import com.smitoi.roomatey.dto.requests.note.UpdateNoteRequest;
import com.smitoi.roomatey.dto.responses.NoteDto;
import com.smitoi.roomatey.entity.Note;
import com.smitoi.roomatey.services.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Notes", description = "Notes related endpoints.")
public class NoteController extends BaseController {

    @Autowired
    private final NoteService service;

    @GetMapping("/{groupId}")
    @Operation(summary = "Index", description = "View all notes from a certain group.")
    public ResponseEntity<List<NoteDto>> index(@PathVariable Long groupId, @RequestParam String search) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getNotesForGroup(groupId, search, getLoggedInUser()));
    }

    @PostMapping("/{groupId}")
    @Operation(summary = "Store", description = "Create a new note inside a group.")
    public ResponseEntity<NoteDto> store(@PathVariable Long groupId, @Valid @RequestBody CreateNoteRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.addNote(request, groupId, getLoggedInUser()));
    }

    @PutMapping("/{noteId}")
    @Operation(summary = "Update", description = "Edit an existing note.")
    public ResponseEntity<NoteDto> update(@PathVariable Long noteId, @Valid @RequestBody UpdateNoteRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.editNote(request, noteId, getLoggedInUser()));
    }
}
