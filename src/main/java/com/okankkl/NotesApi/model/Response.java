package com.okankkl.NotesApi.model;

import jakarta.annotation.Nullable;

public final class Response<T> {

    private final boolean status;
    @Nullable private final T data;
    @Nullable private final String errorMsg;

    private Response(boolean status, @Nullable T data, @Nullable String errorMsg) {
        this.status = status;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public boolean isStatus() { return status; }

    @Nullable
    public T getData() { return data; }

    @Nullable
    public String getErrorMsg() { return errorMsg; }

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
