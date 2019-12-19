package pl.hennigm.lab7.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hennigm.lab7.model.Note;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

    Note findAllById(Long id);
}
