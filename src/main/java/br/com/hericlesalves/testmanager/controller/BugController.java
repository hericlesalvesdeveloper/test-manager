package br.com.hericlesalves.testmanager.controller;

import br.com.hericlesalves.testmanager.dto.bugDto.CreateBugDto;
import br.com.hericlesalves.testmanager.dto.bugDto.ResponseBugDto;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.service.BugService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/bugs")
public class BugController {

    @Autowired
    private BugService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseBugDto> listAll() throws NotFoundException {
        return service.listAll();
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
