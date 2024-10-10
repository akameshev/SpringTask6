package org.project.seminar6homework.services;

import lombok.AllArgsConstructor;
import org.project.seminar6homework.model.Note;
import org.project.seminar6homework.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).get();
    }
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note note) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isPresent()) {
            noteOptional.get().setTitle(note.getTitle());
            noteOptional.get().setContent(note.getContent());
            return noteRepository.save(noteOptional.get());
        }
        return null;
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

}
