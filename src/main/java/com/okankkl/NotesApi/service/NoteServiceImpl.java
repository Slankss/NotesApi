package com.okankkl.NotesApi.service;

import com.okankkl.NotesApi.dto.request.NoteUpdateRequest;
import com.okankkl.NotesApi.dto.response.Response;
import com.okankkl.NotesApi.model.Note;
import com.okankkl.NotesApi.model.NoteFilter;
import com.okankkl.NotesApi.repository.NoteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository){
        this.repository = repository;
    }

    public Response<List<Note>> getNotes(NoteFilter sortType, Sort.Direction direction){
        try {
            var notes = repository.findAll(Sort.by(direction, sortType.dbName));
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

    @Override
    public Response<Void> updateNote(NoteUpdateRequest noteUpdateRequest) {
        try {
            var note = repository.findById(noteUpdateRequest.id()).orElse(null);
            if (note == null){
                return Response.failed("No data with id");
            }

            if (noteUpdateRequest.header() != null){
                note.setHeader(noteUpdateRequest.header());
            }
            if (noteUpdateRequest.content() != null){
                note.setContent(noteUpdateRequest.content());
            }
            if (noteUpdateRequest.priority() != null){
                note.setPriority(noteUpdateRequest.priority());
            }
            repository.save(note);
            return Response.success();
        } catch (Exception e){
            return Response.failed(e.getLocalizedMessage());
        }
    }

    public Response<Void> deleteNoteById(Long id){
        try{
            if (repository.existsById(id)){
                repository.deleteById(id);
                return Response.success();
            }
            return Response.failed("No data with id!");
        }catch (Exception e){
            return Response.failed(e.getLocalizedMessage());
        }
    }

    public Response<Void> deleteAll(){
        try{
            if (repository.count() > 0){
                repository.deleteAll();
                return Response.success();
            }
            return Response.failed("No data!");
        }catch (Exception e){
            return Response.failed(e.getLocalizedMessage());
        }
    }
}
