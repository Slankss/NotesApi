package com.okankkl.NotesApi.dto.request;

import jakarta.validation.constraints.NotNull;

public record NoteUpdateRequest(
        @NotNull Long id,
        String header,
        String content,
        Integer priority
) {
}
