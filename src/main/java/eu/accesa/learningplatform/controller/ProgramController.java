package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.service.ProgramService;
import eu.accesa.learningplatform.service.custom_errors.EntityNotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
@OpenAPIDefinition
@CrossOrigin
@Tag(name = "Program")
public class ProgramController {

    ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<ProgramDto> createProgram(@RequestBody ProgramDto programDto) {
        ProgramDto program = programService.createProgram(programDto);
        if(program != null){
            return new ResponseEntity<>(program, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "found all programs successfully")
    public ResponseEntity<List<ProgramDto>> getAllPrograms() {
        return new ResponseEntity<>(programService.findAllPrograms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "found a program by id successfully")
    public ResponseEntity<ProgramDto> getProgramById(final @PathVariable Long id) {
        ProgramDto programDto = programService.findProgramById(id);
        if(programDto != null){
            return new ResponseEntity<>(programDto,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully updated a program")
    public ResponseEntity<ProgramDto> updateProgram(final @PathVariable Long id, @RequestBody ProgramDto programDto) throws EntityNotFoundException {
        ProgramDto program = programService.updateProgram(programDto, id);
        if(programDto != null){
            return new ResponseEntity<>(program,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted a program by id")
    public ResponseEntity<Long> deleteProgram(final @PathVariable Long id) {
        programService.deleteProgram(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
