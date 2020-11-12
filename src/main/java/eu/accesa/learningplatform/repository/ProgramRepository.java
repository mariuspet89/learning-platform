package eu.accesa.learningplatform.repository;

import eu.accesa.learningplatform.model.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {

    @Query(value = "SELECT p" +
                   "FROM PROGRAM p" +
                   "INNER JOIN ENROLLMENT e " +
                   "WHERE e.USER_ID = :userId")
    List<ProgramEntity> findAllEnrolledProgramsForUser(@Param("userId") Long userId);

}
