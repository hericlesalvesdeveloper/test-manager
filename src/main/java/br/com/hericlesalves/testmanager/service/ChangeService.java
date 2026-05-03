package br.com.hericlesalves.testmanager.service;

import br.com.hericlesalves.testmanager.dto.CreateChangeDto;
import br.com.hericlesalves.testmanager.dto.ResponseChangeDto;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.model.entity.ChangeEntity;
import br.com.hericlesalves.testmanager.repository.ChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeService {

    @Autowired
    private ChangeRepository repository;

    public List<ResponseChangeDto> findAllChangesActive() {
        List<ChangeEntity> entities = repository.findAllActive();

        return entities.stream()
                .map(changeEntity -> new ResponseChangeDto(
                        changeEntity.getName(),
                        changeEntity.getDescription(),
                        changeEntity.getClient(),
                        changeEntity.getPriority(),
                        changeEntity.getStatus()
                )).toList();
    }

    public List<ResponseChangeDto> findByName(String name) {
        List<ChangeEntity> entities = repository.findByName(name);

        return entities.stream()
                .map(changeEntity -> new ResponseChangeDto(
                        changeEntity.getName(),
                        changeEntity.getDescription(),
                        changeEntity.getClient(),
                        changeEntity.getPriority(),
                        changeEntity.getStatus()
                )).toList();
    }

    public ChangeEntity findChangeById(long id) {
        return repository.findById(id).orElse(null);
    }

    public void create(CreateChangeDto changeDto) {

        ChangeEntity change = new ChangeEntity(
                changeDto.getName(),
                changeDto.getClient(),
                changeDto.getDescription(),
                changeDto.getPriority()
        );

        change.changeCreated();
        repository.save(change);
    }

    public void deleteChange(long id) throws NotFoundException {
        var entity = repository.findById(id).orElse(null);

        if (entity == null) throw new NotFoundException("Change not found");

        entity.delete();
        repository.save(entity);
    }

    public void startChange(long id) throws NotFoundException {
        var entity = repository.findById(id).orElse(null);

        if (entity == null) throw new NotFoundException("Change not found");

        entity.startChange();
        repository.save(entity);

    }

    public void pauseChange(long id) throws NotFoundException {
        var entity = repository.findById(id).orElse(null);

        if (entity == null) throw new NotFoundException("Change not found");

        entity.pauseChange();
        repository.save(entity);
    }

    public void doneChange(long id) throws NotFoundException {
        var entity = repository.findById(id).orElse(null);

        if (entity == null) throw new NotFoundException("Change not found");

        entity.doneChange();
        repository.save(entity);
    }

    public void closeChange(long id) throws NotFoundException {
        var entity = repository.findById(id).orElse(null);

        if (entity == null) throw new NotFoundException("Change not found");

        entity.closeChange();
        repository.save(entity);
    }
}
