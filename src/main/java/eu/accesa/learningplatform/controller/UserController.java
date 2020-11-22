package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@OpenAPIDefinition
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/security")
    public String securityMethod() {
        return "O INTRATTTTTTTTTT IN ENDPOINT!!!";
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private UserDto getUserById(@PathVariable final Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/userType/{userTypeId}")
    @ResponseStatus(HttpStatus.OK)
    private List<UserDto> getUsersByUserType(@PathVariable final Long userTypeId) {
        return userService.getUsersByUserType(userTypeId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    private UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteUser(@PathVariable final Long id) {
        userService.deleteUser(id);
    }
}
