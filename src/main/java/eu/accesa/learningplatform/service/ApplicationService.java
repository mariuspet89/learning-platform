package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.ApplicationDto;

import java.util.List;

public interface ApplicationService {

    ApplicationDto createApplication(ApplicationDto applicationDto);

//    List<ApplicationDto> getApplications();
//
//    List<ApplicationDto> getApplicationById(Long Id);
//
//    List<ApplicationDto> getApplicationByStatusId(Long Id);
//
//    List<ApplicationDto> getApplicationByUserId(Long Id);
//
//    ApplicationDto updateStatus(ApplicationDto applicationDto);
//
       void deleteApplication(Long Id);

}
