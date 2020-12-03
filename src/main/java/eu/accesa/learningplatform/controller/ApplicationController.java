package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.model.dto.ApplicationDto;
import eu.accesa.learningplatform.service.ApplicationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/applications")
@OpenAPIDefinition
@Tag(name = "Applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationDto save(@Valid @RequestBody ApplicationDto applicationDto) {

        return applicationService.createApplication(applicationDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.status(HttpStatus.OK).body("Application Deleted");
    }

}
