package org.project.seminar6homework.controllers;

import lombok.AllArgsConstructor;
import org.project.seminar6homework.model.Note;
import org.project.seminar6homework.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        if (notes.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> noteOptional = Optional.ofNullable(noteService.getNoteById(id));
        if (noteOptional.isPresent()) {
            return ResponseEntity.ok(noteOptional.get());
        }
        return ResponseEntity.status(404).build();
    }
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteService.createNote(note);
        return ResponseEntity.status(201).body(createdNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id,@RequestBody Note note) {
        Optional<Note> noteOptional = Optional.ofNullable(noteService.getNoteById(id));
        if (noteOptional.isPresent()) {
            note.setTitle(note.getTitle());
            note.setContent(note.getContent());
            return ResponseEntity.ok(noteService.updateNote(id, note));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable Long id) {
        Optional<Note> noteOptional = Optional.ofNullable(noteService.getNoteById(id));
        if (noteOptional.isPresent()) {
            noteService.deleteNote(id);
        }
        return ResponseEntity.status(404).build();
    }

}
