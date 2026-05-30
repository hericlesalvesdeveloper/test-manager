package br.com.hericlesalves.testmanager.dto.testCaseDto;

import jakarta.validation.constraints.NotBlank;

public record CreateTestCaseDto(
        @NotBlank
        String title,

        @NotBlank
        String expectedResult,

        @NotBlank
        String steps
) {
}
