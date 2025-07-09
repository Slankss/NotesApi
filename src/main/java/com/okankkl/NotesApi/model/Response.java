package com.okankkl.NotesApi.model;

import jakarta.annotation.Nullable;

public record Response<T>(boolean status, @Nullable T data, @Nullable String errorMsg) {

    public static <T> Response<T> success(T data) {
        return new Response<>(true, data, null);
    }

    public static <T> Response<T> success() {
        return new Response<>(true, null, null);
    }

    public static <T> Response<T> failed(String errorMsg) {
        return new Response<>(false, null, errorMsg);
    }
}
