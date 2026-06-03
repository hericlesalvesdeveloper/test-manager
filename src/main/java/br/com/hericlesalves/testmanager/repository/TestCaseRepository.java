package br.com.hericlesalves.testmanager.repository;

import br.com.hericlesalves.testmanager.model.entity.TestCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCaseEntity, Long> {

    @Query("SELECT t FROM TestCaseEntity t WHERE t.deletedAt IS NULL")
    List<TestCaseEntity> findAllActive();

    @Query("SELECT t FROM TestCaseEntity t WHERE t.title LIKE %:title% AND t.deletedAt IS NULL")
    List<TestCaseEntity> likeByTitle(@Param("title") String title);

    @Query("SELECT t FROM TestCaseEntity t WHERE t.title = :title AND t.deletedAt IS NULL")
    TestCaseEntity existsByTitle(@Param("title") String title);
}
