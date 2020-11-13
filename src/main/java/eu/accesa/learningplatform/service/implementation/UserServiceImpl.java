package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.entity.UserEntity;
import eu.accesa.learningplatform.model.entity.UserTypeEnum;
import eu.accesa.learningplatform.repository.UserRepository;
import eu.accesa.learningplatform.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

import static io.swagger.v3.oas.integration.StringOpenApiConfigurationLoader.LOGGER;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        LOGGER.info("Service: creating user with values: {}", userDto);
        UserEntity userEntity = userRepository.save(mapper.map(userDto, UserEntity.class));
        return mapper.map(userEntity, UserDto.class);
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
    public List<UserDto> getUsersByUserType(UserTypeEnum userType) {
        LOGGER.info("Service: retrieving all users with user type: {}", userType);
        List<UserEntity> userEntities = userRepository.findAllByUserTypeEntity_Name(userType);
        return mapper.map(userEntities, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    //TODO: find a way to map dto to entity without null-check on every field
    @Override
    public UserDto updateUser(UserDto userDto) {
        LOGGER.info("Service: updating user with id: {}, with values: {}", userDto.getId(), userDto);

        UserEntity userEntity = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        mapper.map(userDto, userEntity);

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
