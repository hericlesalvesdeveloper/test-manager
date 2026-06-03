package br.com.hericlesalves.testmanager.dto.bugDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBugDto(
        @NotBlank
        String description,

        @NotNull
        Long changeId) {

}
