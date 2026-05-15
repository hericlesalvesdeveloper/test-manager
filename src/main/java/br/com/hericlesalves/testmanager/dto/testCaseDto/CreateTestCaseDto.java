package br.com.hericlesalves.testmanager.dto.testCaseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTestCaseDto {
    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String expectedResult;

    @NotBlank
    @NotNull
    public String steps;

    public CreateTestCaseDto(String title, String expectedResult, String steps) {
        this.title = title;
        this.expectedResult = expectedResult;
        this.steps = steps;
    }
}
