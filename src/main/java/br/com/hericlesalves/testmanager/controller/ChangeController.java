package br.com.hericlesalves.testmanager.controller;

import br.com.hericlesalves.testmanager.dto.changeDto.CreateChangeDto;
import br.com.hericlesalves.testmanager.dto.changeDto.ResponseChangeDto;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.service.ChangeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/changes")
public class ChangeController {

    @Autowired
    private ChangeService changeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseChangeDto> getAllActives() {
        return changeService.findAllChangesActive();
    }

    @GetMapping("/name")
    public List<ResponseChangeDto> getByName(@RequestParam String name) {
        return changeService.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CreateChangeDto change) {
        changeService.create(change);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        changeService.deleteChange(id);
    }

    @PatchMapping("pause/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pauseChange(@PathVariable long id) throws NotFoundException {
        changeService.pauseChange(id);
    }

    @PatchMapping("start/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void startChange(@PathVariable long id) throws NotFoundException {
        changeService.startChange(id);
    }

    @PatchMapping("done/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void doneChange(@PathVariable long id) throws NotFoundException {
        changeService.doneChange(id);
    }

    @PatchMapping("close/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeChange(@PathVariable long id) throws NotFoundException {
        changeService.closeChange(id);
    }
}
