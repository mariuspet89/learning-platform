package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long id);

    List<UserDto> getUsersByUserType (String userType);

    UserDto updateUser(UserDto userDto);
}
