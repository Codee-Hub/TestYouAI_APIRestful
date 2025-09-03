package github.devhub.testyouai.adapter.in.dto;

import java.util.List;

public record Error(
        String status,
        String message,
        List<FieldError> fieldErrors
) {
}
