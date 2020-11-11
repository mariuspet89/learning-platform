package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.entity.UserTypeEnum;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long id);

    List<UserDto> getUsersByUserType (UserTypeEnum userType);

    UserDto updateUser(UserDto userDto);
}
