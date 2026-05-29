package br.com.hericlesalves.testmanager.dto.bugDto;

import br.com.hericlesalves.testmanager.model.enums.BugStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ResponseBugDto {
    @NotNull
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private BugStatus status;
}
