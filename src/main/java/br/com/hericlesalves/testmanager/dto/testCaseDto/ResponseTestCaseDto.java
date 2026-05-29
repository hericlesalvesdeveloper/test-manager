package br.com.hericlesalves.testmanager.dto.testCaseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTestCaseDto {
    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String expectedResult;

    @NotNull
    @NotBlank
    public String steps;

    public ResponseTestCaseDto(String title, String expectedResult, String steps) {
        this.title = title;
        this.steps = steps;
        this.expectedResult = expectedResult;
    }
}
