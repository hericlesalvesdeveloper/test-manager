package br.com.hericlesalves.testmanager.dto.bugDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateBugDto {

    @NotNull
    private String description;

    @NotNull
    private Long changeId;

}
