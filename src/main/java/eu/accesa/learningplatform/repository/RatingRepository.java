package eu.accesa.learningplatform.repository;

import eu.accesa.learningplatform.model.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository  extends JpaRepository<RatingEntity, Long> {
}
