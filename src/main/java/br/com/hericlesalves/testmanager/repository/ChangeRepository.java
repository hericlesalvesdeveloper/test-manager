package br.com.hericlesalves.testmanager.repository;

import br.com.hericlesalves.testmanager.model.entity.ChangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeRepository extends JpaRepository<ChangeEntity, Long> {

    @Query("SELECT c FROM ChangeEntity c WHERE c.deletedAt IS NULL")
    List<ChangeEntity> findAllActive();

    @Query("SELECT c FROM ChangeEntity c WHERE c.name = :name AND c.deletedAt IS NULL")
    List<ChangeEntity> findByName(@Param("name") String name);

}
