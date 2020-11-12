package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.model.entity.ProgramEntity;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.service.ProgramService;
import eu.accesa.learningplatform.service.custom_errors.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ModelMapper modelMapper;

    private final ProgramRepository programRepository;

    private final Logger logger;

    @Autowired
    public ProgramServiceImpl(ModelMapper modelMapper, ProgramRepository programRepository) {
        this.modelMapper = modelMapper;
        this.programRepository = programRepository;
        this.logger = Logger.getLogger(
                ProgramService.class.getName());
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
        List<ProgramEntity> programEntitiesForUser = programRepository.findAllByUserEntities_Id(userId);
        List<ProgramDto> programDtosForUser = programEntitiesForUser
                .stream().map(e -> modelMapper.map(e, ProgramDto.class))
                .collect(Collectors.toList());
        logger.log(Level.INFO, "ProgramDtos for user with id " + userId + ": " + programDtosForUser);
        return programDtosForUser;
    }

    @Override
    public ProgramDto updateProgram(ProgramDto programDto, Long id) throws EntityNotFoundException {

       Optional<ProgramEntity> optionalProgramEntityFromDb = programRepository.findById(id);

       if(optionalProgramEntityFromDb.isEmpty()){
           throw new EntityNotFoundException(ProgramEntity.class.getSimpleName(), id.toString());
       }

       ProgramEntity updatedProgramEntity = modelMapper.map(programDto, ProgramEntity.class);
       programRepository.save(updatedProgramEntity);

       ProgramDto updatedProgramDto = modelMapper.map(updatedProgramEntity, ProgramDto.class);
       return updatedProgramDto;
    }

    @Override
    public void deleteProgram(Long id) {

    }
}
