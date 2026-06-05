package br.com.hericlesalves.testmanager.dto.changeDto;

import br.com.hericlesalves.testmanager.model.enums.ChangePriority;
import br.com.hericlesalves.testmanager.model.enums.ChangeStatus;

public record ResponseChangeDto(
        Long id,
        String name,
        Integer numberChange,
        String description,
        String client,
        ChangePriority priority,
        ChangeStatus changeStatus
) {
}
