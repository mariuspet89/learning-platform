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
import eu.accesa.learningplatform.service.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                        new EntityNotFoundException(CompetenceAreaEntity.class.getSimpleName(),
                                "id",
                                userDto.getCompetenceAreaId().toString()));

        JobTitleEntity jobTitleEntity =
                jobTitleRepository.findById(userDto.getJobTitleId()).orElseThrow(() ->
                        new EntityNotFoundException(JobTitleEntity.class.getSimpleName(),
                                "id",
                                userDto.getJobTitleId().toString()));

        UserTypeEntity userTypeEntity =
                userTypeRepository.findById(userDto.getUserTypeId()).orElseThrow(() ->
                        new EntityNotFoundException(UserTypeEntity.class.getSimpleName(),
                                "id",
                                userDto.getUserTypeId().toString()));

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
                -> new EntityNotFoundException(UserEntity.class.getSimpleName(),
                "id",
                id.toString()));
        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByUserType(Long userTypeId) {
        LOGGER.info("Service: retrieving all users with user type: {}", userTypeId);

        List<UserEntity> userEntities = userRepository.findAllByUserTypeEntity_Id(userTypeId);

        if (userEntities.isEmpty()) {
            throw new EntityNotFoundException(UserEntity.class.getSimpleName(), "UserTypeId", userTypeId.toString());
        }

        return mapper.map(userEntities, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        LOGGER.info("Service: updating user with id: {}, with values: {}", userDto.getId(), userDto.toString());

        UserEntity userEntity = userRepository.findById(userDto.getId())
                .orElseThrow(()
                        -> new EntityNotFoundException(UserEntity.class.getSimpleName(),
                        "id",
                        userDto.getId().toString()));

        mapper.map(userDto, userEntity);

        CompetenceAreaEntity competenceAreaEntity =
                competenceAreaRepository.findById(userDto.getCompetenceAreaId()).orElseThrow(()
                        -> new EntityNotFoundException(CompetenceAreaEntity.class.getSimpleName(),
                        "id",
                        userDto.getCompetenceAreaId().toString()));

        JobTitleEntity jobTitleEntity =
                jobTitleRepository.findById(userDto.getJobTitleId()).orElseThrow(()
                        -> new EntityNotFoundException(JobTitleEntity.class.getSimpleName(),
                        "id",
                        userDto.getJobTitleId().toString()));

        UserTypeEntity userTypeEntity =
                userTypeRepository.findById(userDto.getUserTypeId()).orElseThrow(()
                        -> new EntityNotFoundException(UserTypeEntity.class.getSimpleName(),
                        "id",
                        userDto.getUserTypeId().toString()));

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
                -> new EntityNotFoundException(UserEntity.class.getSimpleName(),
                "id",
                id.toString()));
        userRepository.delete(userEntity);
    }
}
