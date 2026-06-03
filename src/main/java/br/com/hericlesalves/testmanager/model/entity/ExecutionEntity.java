package br.com.hericlesalves.testmanager.model.entity;

import br.com.hericlesalves.testmanager.model.enums.ExecutionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name =  "tb_execution")
@NoArgsConstructor
@Getter
public class ExecutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_case_id", nullable = false)
    private TestCaseEntity testCase;

    @ManyToOne
    @JoinColumn(name = "change_id", nullable = false)
    private ChangeEntity change;

    @Enumerated(EnumType.STRING)
    private ExecutionStatus status;

    private LocalDateTime executedAt;

    public ExecutionEntity(TestCaseEntity testCase, ChangeEntity change) {
        this.testCase = testCase;
        this.change = change;
        this.status = ExecutionStatus.PENDING;
        this.executedAt = LocalDateTime.now();
    }

    public void fail() {
        this.status = ExecutionStatus.FAIL;
    }

    public void pass() {
        this.status = ExecutionStatus.PASS;
    }
}
