package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.repository.UserRepository;
import eu.accesa.learningplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.swagger.v3.oas.integration.StringOpenApiConfigurationLoader.LOGGER;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDto> getUsersByUserType(String userType) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        LOGGER.info("Service: updating user with id: {}, with values: {}", userDto.getId(), userDto);

        userRepository;
    }
}
