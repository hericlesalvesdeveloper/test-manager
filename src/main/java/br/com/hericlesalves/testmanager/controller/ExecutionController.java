package br.com.hericlesalves.testmanager.controller;

import br.com.hericlesalves.testmanager.dto.executionDto.CreateExecution;
import br.com.hericlesalves.testmanager.dto.executionDto.ResponseExecution;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.service.ExecutionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/executions")
public class ExecutionController {

    private final ExecutionService service;

    public ExecutionController(ExecutionService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseExecution> findAll() throws NotFoundException {
        return service.allExecutions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CreateExecution execution) throws NotFoundException {
        service.create(execution);
    }

    @PatchMapping("{id}/success")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void success(@PathVariable Long id) throws NotFoundException {
        service.successiveExecution(id);
    }

    @PatchMapping("{id}/fail")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fail(@PathVariable Long id) throws NotFoundException {
        service.failedExecution(id);
    }
}
