package com.okankkl.NotesApi.service;

import com.okankkl.NotesApi.model.Note;
import com.okankkl.NotesApi.model.Response;
import com.okankkl.NotesApi.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository){
        this.repository = repository;
    }

    public Response<List<Note>> getNotes(){
        try {
            var notes = repository.findAll();
            return Response.success(notes);
        } catch (Exception e){
            return Response.failed(e.getLocalizedMessage());
        }
    }

    public Response<Note> getNoteById(Long id){
        try {
            var note = repository.findById(id);
            if (note.isEmpty()){
                return Response.failed("No valid data!");
            }
            return Response.success(note.orElse(null));
        } catch (Exception e){
            return Response.failed(e.getLocalizedMessage());
        }
    }

    public Response<Void> addNote(Note note){
        try{
            repository.save(note);
            return Response.success();
        }catch (Exception e){
            return Response.failed(e.getLocalizedMessage());
        }
    }

    public Response<Void> deleteNoteById(Long id){
        try{
            repository.deleteById(id);
            return Response.success();
        }catch (Exception e){
            return Response.failed(e.getLocalizedMessage());
        }
    }

    public Response<Void> deleteAll(){
        try{
            repository.deleteAll();
            return Response.success();
        }catch (Exception e){
            return Response.failed(e.getLocalizedMessage());
        }
    }
}
