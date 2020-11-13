package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.model.entity.ProgramEntity;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.service.ProgramService;
import eu.accesa.learningplatform.service.custom_errors.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ModelMapper modelMapper;

    private final ProgramRepository programRepository;

    private final Logger logger = Logger.getLogger(ProgramServiceImpl.class.getName());

    public ProgramServiceImpl(ModelMapper modelMapper, ProgramRepository programRepository) {
        this.modelMapper = modelMapper;
        this.programRepository = programRepository;
    }

    @Override
    public ProgramDto createProgram(ProgramDto programDto) {
        ProgramEntity programEntity = programRepository.save(modelMapper.map(programDto, ProgramEntity.class));
        logger.log(Level.INFO, "Created new program" + programEntity);
        return modelMapper.map(programEntity, ProgramDto.class);
    }

    @Override
    public List<ProgramDto> findAllPrograms() {
        List<ProgramDto> programEntities = programRepository.findAll()
                .stream()
                .map(programEntity -> modelMapper.map(programEntity, ProgramDto.class))
                .collect(Collectors.toList());
        logger.log(Level.INFO, "program entities found: " + programEntities.toString());
        return programEntities;
    }

    @Override
    public ProgramDto findProgramById(Long id) throws EntityNotFoundException {
        Optional<ProgramEntity> programEntity = programRepository.findById(id);
        if (programEntity.isPresent()) {
            logger.log(Level.INFO, "Found program: " + programEntity.get());
        } else {
            throw new EntityNotFoundException(ProgramEntity.class.getSimpleName(), id.toString());
        }
        ProgramDto programDto = modelMapper.map(programEntity, ProgramDto.class);
        return programDto;
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

        if (optionalProgramEntityFromDb.isEmpty()) {
            throw new EntityNotFoundException(ProgramEntity.class.getSimpleName(), id.toString());
        }

        ProgramEntity updatedProgramEntity = modelMapper.map(programDto, ProgramEntity.class);
        programRepository.save(updatedProgramEntity);

        ProgramDto updatedProgramDto = modelMapper.map(updatedProgramEntity, ProgramDto.class);
        return updatedProgramDto;
    }

    @Override
    public void deleteProgram(Long id) {
        logger.log(Level.INFO, "Delete program with id:" + id);
        programRepository.deleteById(id);
    }
}
