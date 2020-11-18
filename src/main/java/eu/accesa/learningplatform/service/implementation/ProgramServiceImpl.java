package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.model.entity.CompetenceAreaEntity;
import eu.accesa.learningplatform.model.entity.ProgramEntity;
import eu.accesa.learningplatform.model.entity.UserEntity;
import eu.accesa.learningplatform.repository.CompetenceAreaRepository;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.service.ProgramService;
import eu.accesa.learningplatform.service.custom_errors.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ModelMapper modelMapper;

    private final ProgramRepository programRepository;

    private final CompetenceAreaRepository competenceAreaRepository;

    private final Logger logger = LoggerFactory.getLogger(ProgramServiceImpl.class.getName());

    public ProgramServiceImpl(ModelMapper modelMapper, ProgramRepository programRepository, CompetenceAreaRepository competenceAreaRepository) {
        this.modelMapper = modelMapper;
        this.programRepository = programRepository;
        this.competenceAreaRepository = competenceAreaRepository;
    }

    @Override
    public ProgramDto createProgram(ProgramDto programDto) {
        ProgramEntity programEntity = modelMapper.map(programDto, ProgramEntity.class);
        CompetenceAreaEntity competenceAreaEntity = competenceAreaRepository.getOne(programDto.getCompetenceAreaId());
        programEntity.setCompetenceAreaEntity(competenceAreaEntity);
        programRepository.save(programEntity);
        logger.info("Created new program" + programEntity);
        return programDto;
    }

    @Override
    public List<ProgramDto> findAllPrograms() {
        List<ProgramDto> programEntities = programRepository.findAll()
                .stream()
                .map(programEntity -> modelMapper.map(programEntity, ProgramDto.class))
                .collect(Collectors.toList());
        logger.info("Program entities found: " + programEntities.toString());
        return programEntities;
    }

    @Override
    public ProgramDto findProgramById(Long id) throws EntityNotFoundException {
        Optional<ProgramEntity> programEntityOptional = programRepository.findById(id);
        if (programEntityOptional.isPresent()) {
            ProgramEntity programEntity = programEntityOptional.get();
            ProgramDto programDto = modelMapper.map(programEntity, ProgramDto.class);
            logger.info("Found program in repo with id: " + id);
            return programDto;
        } else {
            throw new EntityNotFoundException(ProgramDto.class.getSimpleName(), "id", id.toString());
        }
    }

    @Override
    public List<ProgramDto> findAllEnrolledProgramsForUser(Long userId) {
        List<ProgramEntity> programEntitiesForUser = programRepository.findAllByUserEntities_Id(userId);
        List<ProgramDto> programDtosForUser = programEntitiesForUser
                .stream().map(e -> modelMapper.map(e, ProgramDto.class))
                .collect(Collectors.toList());
        logger.info("ProgramDtos for user with id=" + userId + ": " + programDtosForUser);
        return programDtosForUser;
    }

    @Override
    public ProgramDto updateProgram(ProgramDto programDto, Long id) throws EntityNotFoundException {

        Optional<ProgramEntity> optionalProgramEntityFromDb = programRepository.findById(id);

        if (optionalProgramEntityFromDb.isEmpty()) {
            throw new EntityNotFoundException(ProgramEntity.class.getSimpleName(), "id", id.toString());
        }

        ProgramEntity updatedProgramEntity = modelMapper.map(programDto, ProgramEntity.class);
        Long competenceAreaId = programDto.getCompetenceAreaId();
        Optional<CompetenceAreaEntity> competenceAreaEntityOptional = competenceAreaRepository.findById(competenceAreaId);

        if (competenceAreaEntityOptional.isEmpty()) {
            throw new EntityNotFoundException(CompetenceAreaEntity.class.getSimpleName(), "id", competenceAreaId.toString());
        }

        updatedProgramEntity.setCompetenceAreaEntity(competenceAreaEntityOptional.get());
        logger.info("saving to repo the updated program with id= " + id);
        programRepository.save(updatedProgramEntity);

        ProgramDto updatedProgramDto = modelMapper.map(updatedProgramEntity, ProgramDto.class);
        return updatedProgramDto;
    }

    @Override
    public void deleteProgram(Long id) throws EntityNotFoundException {
        logger.info("Deleting program with id:" + id);
        Optional<ProgramEntity> programEntityOptional = programRepository.findById(id);
        if (programEntityOptional.isPresent()) {
            for (UserEntity userEntity : programEntityOptional.get().getUserEntities()) {
                userEntity.getProgramEntities().remove(programEntityOptional.get());
            }
            programEntityOptional.get().getUserEntities().clear();
            programRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(ProgramEntity.class.getSimpleName(), "id", id.toString());
        }
    }
}
