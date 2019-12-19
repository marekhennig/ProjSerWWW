package pl.hennigm.lab7.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hennigm.lab7.model.Note;

import java.util.List;

@Component
public class NoteService {
    @Autowired
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    private NoteRepo noteRepo;

    public void saveNote(String text) {
        Note note = new Note(text);
        noteRepo.save(note);
    }

    public void updateNote(Note note, String newText) {
        note.setText(newText);
        noteRepo.save(note);
    }

    public List<Note> findAllNote() {
        List<Note> note = noteRepo.findAll();
        return note;
    }

    public Note findNoteByID(Long id) {
        Note note = noteRepo.findAllById(id);
        return note;
    }

  }


