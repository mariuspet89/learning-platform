package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.service.ProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    private ModelMapper modelMapper;

    private ProgramRepository programRepository;

    public ProgramServiceImpl(ModelMapper modelMapper, ProgramRepository programRepository) {
        this.modelMapper = modelMapper;
        this.programRepository = programRepository;
    }

    @Override
    public ProgramDto createProgram(ProgramDto programDto) {
        return null;
    }

    @Override
    public List<ProgramDto> findAllPrograms() {
        return null;
    }

    @Override
    public ProgramDto findProgramById(Long id) {
        return null;
    }

    @Override
    public List<ProgramDto> findAllEnrolledProgramsForUser(Long userId) {
        return null;
    }

    @Override
    public ProgramDto updateProgram(ProgramDto programDto, Long id) {
        return null;
    }

    @Override
    public void deleteProgram(Long id) {

    }
}
