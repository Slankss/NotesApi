package com.okankkl.NotesApi.controller;

import com.okankkl.NotesApi.model.Note;
import com.okankkl.NotesApi.model.Response;
import com.okankkl.NotesApi.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notes/api")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service){
        this.service = service;
    }

    @GetMapping("/")
    public Response<List<Note>> getNotes(){
        return service.getNotes();
    }

    @GetMapping("/{id}")
    public Response<Note> getNote(@PathVariable String id){
        return service.getNoteById(Long.parseLong(id));
    }

    @PostMapping("/")
    public Response<Void> addNote(@RequestBody Note note){
        return service.addNote(note);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteNote(@PathVariable String id){
        return service.deleteNoteById(Long.parseLong(id));
    }
}
