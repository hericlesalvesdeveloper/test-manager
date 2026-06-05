package br.com.hericlesalves.testmanager.service;

import br.com.hericlesalves.testmanager.dto.changeDto.CreateChangeDto;
import br.com.hericlesalves.testmanager.dto.changeDto.ResponseChangeDto;
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
                        changeEntity.getNumberChange(),
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
                        changeEntity.getNumberChange(),
                        changeEntity.getDescription(),
                        changeEntity.getClient(),
                        changeEntity.getPriority(),
                        changeEntity.getStatus()
                )).toList();
    }

    public ResponseChangeDto findChangeById(long id) throws NotFoundException {
        var entity = repository.findById(id).orElse(null);

        if(entity == null) {
            throw new NotFoundException("Change not found");
        }

        return new ResponseChangeDto(
                entity.getName().trim(),
                entity.getNumberChange(),
                entity.getDescription().trim(),
                entity.getClient().trim(),
                entity.getPriority(),
                entity.getStatus()
        );
    }

    public void create(CreateChangeDto changeDto) {

        ChangeEntity change = new ChangeEntity(
                changeDto.numberChange(),
                changeDto.name(),
                changeDto.client(),
                changeDto.description(),
                changeDto.priority()
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
