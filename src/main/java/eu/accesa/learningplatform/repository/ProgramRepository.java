package eu.accesa.learningplatform.repository;

import eu.accesa.learningplatform.model.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {
}
