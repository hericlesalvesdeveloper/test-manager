package br.com.hericlesalves.testmanager.controller;

import br.com.hericlesalves.testmanager.dto.testCaseDto.CreateTestCaseDto;
import br.com.hericlesalves.testmanager.dto.testCaseDto.ResponseTestCaseDto;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.service.TestCaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/tests")
public class TestCaseController {

    private final TestCaseService service;

    public TestCaseController(TestCaseService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseTestCaseDto> findAllActives() throws NotFoundException {
        return service.findAllActive();
    }

    @GetMapping("/title")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTestCaseDto findByTitle(@RequestParam String title) throws NotFoundException {
        return service.findByTitle(title);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseTestCaseDto findById(@PathVariable Long id) throws NotFoundException {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTest(@RequestBody @Valid CreateTestCaseDto testCaseDto) {
        service.create(testCaseDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTest(@PathVariable Long id) throws NotFoundException {
        service.delete(id);
    }
}
