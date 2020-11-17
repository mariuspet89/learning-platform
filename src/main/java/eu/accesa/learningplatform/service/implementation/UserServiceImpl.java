package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.LearningPlatformApplication;
import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.entity.CompetenceAreaEntity;
import eu.accesa.learningplatform.model.entity.JobTitleEntity;
import eu.accesa.learningplatform.model.entity.UserEntity;
import eu.accesa.learningplatform.model.entity.UserTypeEntity;
import eu.accesa.learningplatform.repository.CompetenceAreaRepository;
import eu.accesa.learningplatform.repository.JobTitleRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import eu.accesa.learningplatform.repository.UserTypeRepository;
import eu.accesa.learningplatform.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LearningPlatformApplication.class);

    private final UserRepository userRepository;

    private final CompetenceAreaRepository competenceAreaRepository;

    private final JobTitleRepository jobTitleRepository;

    private final UserTypeRepository userTypeRepository;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CompetenceAreaRepository competenceAreaRepository, JobTitleRepository jobTitleRepository, UserTypeRepository userTypeRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.competenceAreaRepository = competenceAreaRepository;
        this.jobTitleRepository = jobTitleRepository;
        this.userTypeRepository = userTypeRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        LOGGER.info("Service: creating user with values: {}", userDto.toString());

        UserEntity userEntity = mapper.map(userDto, UserEntity.class);

        CompetenceAreaEntity competenceAreaEntity =
                competenceAreaRepository.findById(userDto.getCompetenceAreaId()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Competence area doesn't exist."));

        JobTitleEntity jobTitleEntity =
                jobTitleRepository.findById(userDto.getJobTitleId()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Job title doesn't exist."));

        UserTypeEntity userTypeEntity =
                userTypeRepository.findById(userDto.getUserTypeId()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User type doesn't exist."));

        userEntity.setCompetenceAreaEntity(competenceAreaEntity);
        userEntity.setJobTitleEntity(jobTitleEntity);
        userEntity.setUserTypeEntity(userTypeEntity);

        return mapper.map(userRepository.save(userEntity), UserDto.class);

    }

    @Override
    public List<UserDto> getAllUsers() {
        LOGGER.info("Service: retrieving all users");
        return mapper.map(userRepository.findAll(), new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @Override
    public UserDto getUserById(Long id) {
        LOGGER.info("Service: retrieving user with id: {}", id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByUserType(Long userTypeId) {
        LOGGER.info("Service: retrieving all users with user type: {}", userTypeId);

        List<UserEntity> userEntities = userRepository.findAllByUserTypeEntity_Id(userTypeId);
        return mapper.map(userEntities, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    //TODO: find a way to map dto to entity without null-check on every field
    @Override
    public UserDto updateUser(UserDto userDto) {
        LOGGER.info("Service: updating user with id: {}, with values: {}", userDto.getId(), userDto.toString());

        UserEntity userEntity = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        mapper.map(userDto, userEntity);

        CompetenceAreaEntity competenceAreaEntity =
                competenceAreaRepository.findById(userDto.getCompetenceAreaId()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Competence area doesn't exist."));

        JobTitleEntity jobTitleEntity =
                jobTitleRepository.findById(userDto.getJobTitleId()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Job title doesn't exist."));

        UserTypeEntity userTypeEntity =
                userTypeRepository.findById(userDto.getUserTypeId()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User type doesn't exist."));

        userEntity.setCompetenceAreaEntity(competenceAreaEntity);
        userEntity.setJobTitleEntity(jobTitleEntity);
        userEntity.setUserTypeEntity(userTypeEntity);

        userRepository.save(userEntity);

        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        LOGGER.info("Service: deleting the user with id: {} ", id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));
        userRepository.delete(userEntity);
    }
}
