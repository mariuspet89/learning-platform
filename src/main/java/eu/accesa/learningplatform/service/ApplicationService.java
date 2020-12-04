package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.ApplicationDto;
import eu.accesa.learningplatform.model.entity.ApplicationStatusEnum;

import java.util.List;

public interface ApplicationService {

    ApplicationDto createApplication(ApplicationDto applicationDto);

    List<ApplicationDto> getAllApplications();

    ApplicationDto getApplicationById(Long Id);

    List<ApplicationDto> getApplicationByStatus(ApplicationStatusEnum status);

//   List<ApplicationDto> getApplicationByUserId(Long Id);

//   ApplicationDto updateStatus(ApplicationDto applicationDto);

    void deleteApplication(Long Id);

}
