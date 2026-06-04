package br.com.hericlesalves.testmanager.controller;

import br.com.hericlesalves.testmanager.dto.bugDto.CreateBugDto;
import br.com.hericlesalves.testmanager.dto.bugDto.ResponseBugDto;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.service.BugService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/bugs")
public class BugController {

    private final BugService service;

    public BugController(BugService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseBugDto> listAll(Pageable pageable) throws NotFoundException {
        return service.listAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CreateBugDto dto) throws NotFoundException {
        service.create(dto);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeBug(@PathVariable Long id) throws NotFoundException {
        service.closeBug(id);
    }
}
