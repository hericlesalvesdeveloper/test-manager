package br.com.hericlesalves.testmanager.service;

import br.com.hericlesalves.testmanager.dto.testCaseDto.CreateTestCaseDto;
import br.com.hericlesalves.testmanager.dto.testCaseDto.ResponseTestCaseDto;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.model.entity.TestCaseEntity;
import br.com.hericlesalves.testmanager.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository repository;

    public List<ResponseTestCaseDto> findAllActive() throws NotFoundException {
        List<TestCaseEntity> entities = repository.findAllActive();

        if(entities.isEmpty()) {
            throw new NotFoundException("There are no active test cases at the moment");
        }

        return entities.stream()
                .map(testCase -> new ResponseTestCaseDto(
                        testCase.getTitle().trim(),
                        testCase.getExpectedResult().trim(),
                        testCase.getSteps().trim()
                )).toList();
    }

    public void create(CreateTestCaseDto createTestCaseDto) {

        TestCaseEntity testCase = new TestCaseEntity(

                createTestCaseDto.title(),
                createTestCaseDto.steps(),
                createTestCaseDto.expectedResult()
        );

        if (repository.existsByTitle(testCase.getTitle().trim()) != null) {
            throw new IllegalArgumentException("There is already a record with this title");
        }

        try {
            repository.save(testCase);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("There is already a record with this title");
        }
    }

    public ResponseTestCaseDto findById(Long id) throws NotFoundException {
        var entity = repository.findById(id).orElse(null);

        if (entity == null) {
            throw new NotFoundException("Test Case not found!");
        }

        return new ResponseTestCaseDto(
                entity.getTitle().trim(),
                entity.getSteps().trim(),
                entity.getExpectedResult().trim()
        );
    }

    public void delete(long id) throws NotFoundException {
        var entity = repository.findById(id).orElse(null);

        if (entity == null) {
            throw new NotFoundException("Test Case not found!");
        }

        entity.delete();

        repository.save(entity);
    }

    public List<ResponseTestCaseDto> likeByTitle(String title) throws NotFoundException {
        var entity = repository.likeByTitle(title.trim());

        if (entity.isEmpty()) {
            throw new NotFoundException("Test Case not found with this title!");
        }

        return entity.stream()
                .map(testCase -> new ResponseTestCaseDto(
                        testCase.getTitle(),
                        testCase.getExpectedResult(),
                        testCase.getSteps()
                )).toList();
    }

    public ResponseTestCaseDto findByTitle(String title) throws NotFoundException {
        var entity = repository.existsByTitle(title);

        if(entity == null) {
            throw new NotFoundException("Test case not found");
        }

        return new ResponseTestCaseDto(
                entity.getTitle(),
                entity.getExpectedResult(),
                entity.getSteps()
        );
    }
}