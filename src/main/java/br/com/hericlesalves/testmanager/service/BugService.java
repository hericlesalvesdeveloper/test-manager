package br.com.hericlesalves.testmanager.service;

import br.com.hericlesalves.testmanager.dto.bugDto.CreateBugDto;
import br.com.hericlesalves.testmanager.dto.bugDto.ResponseBugDto;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.model.entity.BugEntity;
import br.com.hericlesalves.testmanager.model.entity.ChangeEntity;
import br.com.hericlesalves.testmanager.repository.BugRepository;
import br.com.hericlesalves.testmanager.repository.ChangeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BugService {

    private final BugRepository bugRepository;
    private final ChangeRepository changeRepository;

    public BugService(BugRepository bugRepository, ChangeRepository changeRepository) {
        this.bugRepository = bugRepository;
        this.changeRepository = changeRepository;
    }

    public void create(CreateBugDto bugDto) throws NotFoundException {
        ChangeEntity change = changeRepository.findById(bugDto.getChangeId())
                .orElseThrow(() -> new NotFoundException("Change not found!"));

        BugEntity bugEntity = new BugEntity(
                bugDto.getDescription(),
                change
        );

        bugRepository.save(bugEntity);
    }

    public List<ResponseBugDto> listAll() throws NotFoundException {
        var entity = bugRepository.findAll();

        if(entity.isEmpty()) throw new NotFoundException("Bug not found");

        return entity.stream()
                .map(bugEntity -> new ResponseBugDto(
                        bugEntity.getId(),
                        bugEntity.getDescription(),
                        bugEntity.getStatus()
                )).toList();
    }

    public void closeBug(Long id) throws NotFoundException {
        var entity = bugRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bug not found!"));

        entity.close();
        bugRepository.save(entity);

    }
}
