package br.com.hericlesalves.testmanager.dto.changeDto;

import br.com.hericlesalves.testmanager.model.enums.ChangePriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateChangeDto(
        @NotBlank
        String name,

        @NotNull
        Integer numberChange,

        @NotBlank
        String description,

        @NotBlank
        String client,

        @NotNull
        ChangePriority priority)
{
}
