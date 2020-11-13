package eu.accesa.learningplatform.repository;

import eu.accesa.learningplatform.model.entity.CompetenceAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceAreaRepository extends JpaRepository<CompetenceAreaEntity, Long> {
}
