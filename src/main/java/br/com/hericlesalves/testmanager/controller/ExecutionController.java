package br.com.hericlesalves.testmanager.controller;

import br.com.hericlesalves.testmanager.dto.executionDto.CreateExecution;
import br.com.hericlesalves.testmanager.dto.executionDto.ResponseExecution;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.service.ExecutionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/executions")
public class ExecutionController {

    private final ExecutionService service;

    public ExecutionController(ExecutionService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseExecution> findAll(Pageable pageable) throws NotFoundException {
        return service.allExecutions(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseExecution> create(@RequestBody @Valid CreateExecution execution,
                                                    UriComponentsBuilder componentsBuilder) throws NotFoundException {
       var createdExecution = service.create(execution);

        var uri = componentsBuilder.path("v1/executions/{id}").buildAndExpand(createdExecution.id()).toUri();

        return ResponseEntity.created(uri).body(createdExecution);

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
