package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.entity.UserTypeEnum;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getUsersByUserType(UserTypeEnum userType);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long id);
}
