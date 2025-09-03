package github.devhub.testyouai.adapter.in.dto;

import java.util.List;

public record Error(
        Integer status,
        String message,
        List<FieldWithError> fieldWithErrors
) {
}
