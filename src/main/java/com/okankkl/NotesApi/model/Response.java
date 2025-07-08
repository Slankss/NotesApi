package com.okankkl.NotesApi.model;

public final class Response<T> {

    private Response(boolean status, T data, String errorMsg) {
    }

    public static <T> Response<T> success(T data){
        return new Response<>(true,data,null);
    }

    public static <T> Response<T> success(){
        return new Response<>(true,null,null);
    }

    public static <T> Response<T> failed(String errorMsg){
        return new Response<>(false,null,errorMsg);
    }
}
