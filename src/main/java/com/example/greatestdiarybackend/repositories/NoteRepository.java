package com.example.greatestdiarybackend.repositories;

import com.example.greatestdiarybackend.entities.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findNotesByUserId(Long id);
    Note findNoteByUuid(String uuid);
}
