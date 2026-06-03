package br.com.hericlesalves.testmanager.dto.bugDto;

import br.com.hericlesalves.testmanager.model.enums.BugStatus;

public record ResponseBugDto(
        Long id,
        String description,
        BugStatus status) {

}
