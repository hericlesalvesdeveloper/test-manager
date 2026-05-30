package br.com.hericlesalves.testmanager.dto.executionDto;

import br.com.hericlesalves.testmanager.model.enums.ExecutionStatus;
import java.time.LocalDateTime;

public record ResponseExecution(
       Long id,
       ExecutionStatus status,
       LocalDateTime executedAt

) {
}
