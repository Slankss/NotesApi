package com.okankkl.NotesApi.controller;

import com.okankkl.NotesApi.dto.request.NoteUpdateRequest;
import com.okankkl.NotesApi.dto.response.Response;
import com.okankkl.NotesApi.model.Note;
import com.okankkl.NotesApi.model.NoteFilter;
import com.okankkl.NotesApi.service.NoteServiceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("notes/api")
public class NoteController {

    private final NoteServiceImpl service;

    public NoteController(NoteServiceImpl service){
        this.service = service;
    }

    /**
     * @param filter the filtering criteria; possible values are:
     *               <ul>
     *                  <li><b>priority</b> - sort notes by priority</li>
     *                  <li><b>date</b> - sort notes by creation date(default)</li>
     *               </ul>
     * @param sortDirection the sorting criteria; possible values:
     *               <ul>
     *                  <li><b>asc</b> - sort notes by decline</li>
     *                  <li><b>desc</b> - sort notes by increment</li>
     *               </ul>
     */
    @GetMapping("/")
    public Response<List<Note>> getNotes(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String sortDirection
    ){
        NoteFilter noteFilter = NoteFilter.ID;
        Sort.Direction direction = Sort.Direction.DESC;

        if (filter != null){
            if (filter.toUpperCase(Locale.ENGLISH).equals(NoteFilter.PRIORITY.name())){
                noteFilter = NoteFilter.PRIORITY;
            } else if(filter.toUpperCase(Locale.ENGLISH).equals(NoteFilter.DATE.name())) {
                noteFilter = NoteFilter.DATE;
            }
        }

        if (sortDirection != null && sortDirection.toUpperCase().equals(Sort.Direction.ASC.name())) {
            direction = Sort.Direction.ASC;
        }

        return service.getNotes(noteFilter,direction);
    }

    @GetMapping("/{id}")
    public Response<Note> getNote(@PathVariable String id){
        return service.getNoteById(Long.parseLong(id));
    }

    @PostMapping("/")
    public Response<Void> addNote(@RequestBody Note note){

        // Validate priority
        if (note.getPriority() == null || note.getPriority() <= 0 || note.getPriority() > 10){
            note.setPriority(1);
        }

        return service.addNote(note);
    }

    @PatchMapping("/")
    public Response<Void> updateNote(@RequestBody NoteUpdateRequest request){
        return service.updateNote(request);
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteNote(@PathVariable String id){
        return service.deleteNoteById(Long.parseLong(id));
    }

    @DeleteMapping("/all")
    public Response<Void> deleteNote(){
        return service.deleteAll();
    }
}
