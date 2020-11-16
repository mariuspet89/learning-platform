package eu.accesa.learningplatform.repository;

import eu.accesa.learningplatform.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

}
