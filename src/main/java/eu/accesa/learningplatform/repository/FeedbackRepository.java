package eu.accesa.learningplatform.repository;

import eu.accesa.learningplatform.model.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity,Long> {

}
