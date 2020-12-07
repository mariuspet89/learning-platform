package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.dto.UserDtoForGetCalls;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getUsersByUserType(Long userTypeId);

    List<UserDto> getAllUsers();

    List<UserDto> getAllUsersByProgram(Long programId);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long id);
}
