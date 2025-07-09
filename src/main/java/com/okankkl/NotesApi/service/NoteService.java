package com.okankkl.NotesApi.service;

import com.okankkl.NotesApi.dto.request.NoteUpdateRequest;
import com.okankkl.NotesApi.dto.response.Response;
import com.okankkl.NotesApi.model.Note;
import com.okankkl.NotesApi.model.NoteFilter;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface NoteService {
    Response<List<Note>> getNotes(NoteFilter sortType, Sort.Direction direction);
    Response<Note> getNoteById(Long id);
    Response<Void> addNote(Note note);
    Response<Void> updateNote(NoteUpdateRequest noteUpdateRequest);
    Response<Void> deleteNoteById(Long id);
    Response<Void> deleteAll();
}
