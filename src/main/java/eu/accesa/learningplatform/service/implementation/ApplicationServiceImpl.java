package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.exceptionhandler.LearningPlatformException;
import eu.accesa.learningplatform.model.dto.ApplicationDto;
import eu.accesa.learningplatform.model.dto.FeedbackDto;
import eu.accesa.learningplatform.model.entity.ApplicationEntity;
import eu.accesa.learningplatform.model.entity.ApplicationStatusEnum;
import eu.accesa.learningplatform.model.entity.FeedbackEntity;
import eu.accesa.learningplatform.repository.ApplicationRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import eu.accesa.learningplatform.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    private final ApplicationRepository applicationRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  UserRepository userRepository, ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApplicationDto createApplication(ApplicationDto applicationDto) {

        LOGGER.info("Creating Application" + applicationDto.getId());

        ApplicationEntity applicationEntity =
                applicationRepository.save(modelMapper.map(applicationDto, ApplicationEntity.class));

        return modelMapper.map(applicationEntity, ApplicationDto.class);

    }

    @Override
    public List<ApplicationDto> getAllApplications() {

        LOGGER.info("Searching for all Applications");

        List<ApplicationEntity> applicationEntities = applicationRepository.findAll();

        return applicationEntities.stream()
                .map(app -> modelMapper.map(app, ApplicationDto.class))
                .collect(toList());
    }

    @Override
    public ApplicationDto getApplicationById(Long Id) {

        LOGGER.info("Searching for the Application with the " + Id);

        ApplicationEntity applicationEntity = applicationRepository.findById(Id)
                .orElseThrow(()
                        -> new LearningPlatformException("Application Not Found with the following ID:" + Id));
        return modelMapper.map(applicationEntity, ApplicationDto.class);

    }

    @Override
    public List<ApplicationDto> getApplicationByStatus(ApplicationStatusEnum status) {

        List<ApplicationEntity> applicationEntities = applicationRepository.getApplicationByStatus(status);

        List<ApplicationDto> applicationDtoList = applicationEntities.stream()
                .map(app -> modelMapper.map(app, ApplicationDto.class))
                .collect(toList());

        return applicationDtoList;
    }


    @Override
    public void deleteApplication(Long Id) {

        LOGGER.info("Deleting application with the following ID: " + Id);

        ApplicationEntity applicationEntity = applicationRepository.findById(Id).orElseThrow(()
                -> new LearningPlatformException("Application Not Found with the following ID:" + Id));

        applicationRepository.delete(applicationEntity);
    }
}
