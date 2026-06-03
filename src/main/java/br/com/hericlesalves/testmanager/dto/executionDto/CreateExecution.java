package br.com.hericlesalves.testmanager.dto.executionDto;

import jakarta.validation.constraints.NotNull;

public record CreateExecution(
        @NotNull
        Long testCaseId,
        @NotNull
        Long changeId
) {
}
