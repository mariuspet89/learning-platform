package eu.accesa.learningplatform.repository;

import eu.accesa.learningplatform.model.dto.ApplicationDto;
import eu.accesa.learningplatform.model.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity,Long> {

//    List<ApplicationDto> getApplicationByUserEntity_Id(Long Id);
//
//    List<ApplicationDto> ApplicationStatusEnum_Id(Long Id);


}
