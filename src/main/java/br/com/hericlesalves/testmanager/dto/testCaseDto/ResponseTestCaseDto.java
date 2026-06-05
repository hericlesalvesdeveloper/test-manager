package br.com.hericlesalves.testmanager.dto.testCaseDto;

import jakarta.validation.constraints.NotBlank;

public record ResponseTestCaseDto(
        Long id,
        String title,
        String expectedResult,
        String steps
) {
}
