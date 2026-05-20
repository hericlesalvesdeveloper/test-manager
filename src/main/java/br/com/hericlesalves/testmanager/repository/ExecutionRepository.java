package br.com.hericlesalves.testmanager.repository;

import br.com.hericlesalves.testmanager.model.entity.ExecutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionRepository extends JpaRepository<ExecutionEntity, Long> {
}
