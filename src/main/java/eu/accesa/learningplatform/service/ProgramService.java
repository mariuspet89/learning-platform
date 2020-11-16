package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.service.custom_errors.EntityNotFoundException;

import java.util.List;

public interface ProgramService {

    ProgramDto createProgram(ProgramDto programDto);

    List<ProgramDto> findAllPrograms();

    ProgramDto findProgramById(Long id) throws EntityNotFoundException;

    List<ProgramDto> findAllEnrolledProgramsForUser(Long userId);

    ProgramDto updateProgram(ProgramDto programDto, Long id) throws EntityNotFoundException;

    void deleteProgram(Long id) throws EntityNotFoundException;


}
