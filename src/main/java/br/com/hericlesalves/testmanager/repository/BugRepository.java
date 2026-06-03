package br.com.hericlesalves.testmanager.repository;

import br.com.hericlesalves.testmanager.model.entity.BugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<BugEntity, Long> {
}
