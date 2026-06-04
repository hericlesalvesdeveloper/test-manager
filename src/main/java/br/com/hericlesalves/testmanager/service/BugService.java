package br.com.hericlesalves.testmanager.service;

import br.com.hericlesalves.testmanager.dto.bugDto.CreateBugDto;
import br.com.hericlesalves.testmanager.dto.bugDto.ResponseBugDto;
import br.com.hericlesalves.testmanager.exceptions.NotFoundException;
import br.com.hericlesalves.testmanager.model.entity.BugEntity;
import br.com.hericlesalves.testmanager.model.entity.ChangeEntity;
import br.com.hericlesalves.testmanager.repository.BugRepository;
import br.com.hericlesalves.testmanager.repository.ChangeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        ChangeEntity change = changeRepository.findById(bugDto.changeId())
                .orElseThrow(() -> new NotFoundException("Change not found!"));

        BugEntity bugEntity = new BugEntity(
                bugDto.description(),
                change
        );

        bugRepository.save(bugEntity);
    }

    public Page<ResponseBugDto> listAll(Pageable pageable) throws NotFoundException {
        var entity = bugRepository.findAll(pageable);

        if (entity.isEmpty()) throw new NotFoundException("Bug not found");

        return entity
                .map(bugEntity -> new ResponseBugDto(
                        bugEntity.getId(),
                        bugEntity.getDescription(),
                        bugEntity.getStatus()
                ));
    }

    public void closeBug(Long id) throws NotFoundException {
        var entity = bugRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bug not found!"));

        entity.close();
        bugRepository.save(entity);

    }
}
