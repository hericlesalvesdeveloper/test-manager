package br.com.hericlesalves.testmanager.dto.executionDto;

import br.com.hericlesalves.testmanager.model.enums.ExecutionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ResponseExecution {
    private Long id;
    private ExecutionStatus status;
    private LocalDateTime executedAt;

    public ResponseExecution(Long id, ExecutionStatus status, LocalDateTime executedAt) {
        this.id = id;
        this.status = status;
        this.executedAt = executedAt;
    }
}
