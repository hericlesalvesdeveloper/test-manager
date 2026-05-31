package br.com.hericlesalves.testmanager.service;

import br.com.hericlesalves.testmanager.dto.executionDto.CreateExecution;
import br.com.hericlesalves.testmanager.dto.executionDto.ResponseExecution;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.model.entity.ChangeEntity;
import br.com.hericlesalves.testmanager.model.entity.ExecutionEntity;
import br.com.hericlesalves.testmanager.model.entity.TestCaseEntity;
import br.com.hericlesalves.testmanager.repository.ChangeRepository;
import br.com.hericlesalves.testmanager.repository.ExecutionRepository;
import br.com.hericlesalves.testmanager.repository.TestCaseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExecutionService {

    private final ChangeRepository changeRepository;
    private final TestCaseRepository testCaseRepository;
    private final ExecutionRepository executionRepository;

    public ExecutionService(ChangeRepository changeRepository,
                            TestCaseRepository testCaseRepository,
                            ExecutionRepository executionRepository) {
        this.changeRepository = changeRepository;
        this.testCaseRepository = testCaseRepository;
        this.executionRepository = executionRepository;
    }

    public void create(CreateExecution execution) throws NotFoundException {

        TestCaseEntity testCase =
                testCaseRepository.findById(execution.testCaseId())
                        .orElseThrow(() -> new NotFoundException("Test case not found"));

        ChangeEntity change =
                changeRepository.findById(execution.changeId())
                        .orElseThrow(() -> new NotFoundException("Change not found"));

        ExecutionEntity executionEntity = new ExecutionEntity(testCase, change);

        executionRepository.save(executionEntity);

    }

    public void successiveExecution(Long id) throws NotFoundException {
        var execution = executionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Execution not found"));

        execution.pass();

        executionRepository.save(execution);
    }

    public void failedExecution(Long id) throws NotFoundException {
        var execution = executionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Execution not found"));

        execution.fail();

        executionRepository.save(execution);
    }

    public List<ResponseExecution> allExecutions() throws NotFoundException {
        var entity = executionRepository.findAll();

        if(entity.isEmpty()) throw new NotFoundException("Execution not found!");

        return entity.stream()
                .map(executionEntity -> new ResponseExecution(
                        executionEntity.getId(),
                        executionEntity.getStatus(),
                        executionEntity.getExecutedAt()
                )).toList();
    }
}
