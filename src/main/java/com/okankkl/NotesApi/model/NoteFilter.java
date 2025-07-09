package com.okankkl.NotesApi.model;

public enum NoteFilter{
    PRIORITY("priority"),
    DATE("createdDate"),
    ID("id");

    public final String dbName;

    NoteFilter(String dbName){
        this.dbName = dbName;
    }
}