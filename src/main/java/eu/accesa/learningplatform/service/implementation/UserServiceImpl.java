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

import java.util.List;

import static io.swagger.v3.oas.integration.StringOpenApiConfigurationLoader.LOGGER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDto getUserById(Long id) {
        LOGGER.info("Service: retrieving user with id: {}", id);
        UserDto userDto = mapper.map(userRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found")), UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getUsersByUserType(UserTypeEnum userType) {
        LOGGER.info("Service: retrieving all users with user type: {}", userType);
        List<UserDto> userDtos = mapper.map(userRepository.findAllByUserTypeEntity_Name(userType),
                new TypeToken<List<UserDto>>() {}.getType());
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        LOGGER.info("Service: updating user with id: {}, with values: {}", userDto.getId(), userDto);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        UserDto updatedUserDto = mapper.map(userRepository.save(userEntity), UserDto.class);
        return updatedUserDto;
    }
}
