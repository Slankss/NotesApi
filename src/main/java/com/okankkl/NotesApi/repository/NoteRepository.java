package com.okankkl.NotesApi.repository;

import com.okankkl.NotesApi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
}
