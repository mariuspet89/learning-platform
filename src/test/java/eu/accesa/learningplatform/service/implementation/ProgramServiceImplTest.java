package eu.accesa.learningplatform.service.implementation;

import ch.qos.logback.classic.spi.LoggingEvent;
import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.model.entity.*;
import eu.accesa.learningplatform.repository.CompetenceAreaRepository;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static eu.accesa.learningplatform.utils.TestUtils.*;
import static eu.accesa.learningplatform.utils.TestUtils.testUtilsLessonDtoNoId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
class ProgramServiceImplTest {
    @Mock
    private ProgramRepository programRepository;

    @Mock
    private CompetenceAreaRepository competenceAreaRepository;

    @Mock
    private Logger LOGGER = LoggerFactory.getLogger(LessonServiceImplTest.class);

    @Spy
    private ModelMapper mapper;
    @Captor
    private ArgumentCaptor<LoggingEvent> loadArgumentCaptor;

    @InjectMocks
    private ProgramServiceImpl programServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveProgram(){
        // given
        CompetenceAreaEntity competenceAreaEntity = TestUtils.createCompetenceArea(1L);
        ProgramEntity programEntity = TestUtils.initializeProgram("Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                competenceAreaEntity);
        ProgramEntity createdProgramEntity = TestUtils.initializeProgram(1L,
                "Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                competenceAreaEntity);
        ProgramDto programDto = TestUtils.initializeProgramDto("Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                1L);
        // when
        when(competenceAreaRepository.findById(programEntity.getCompetenceAreaEntity().getId())).thenReturn(Optional.of(competenceAreaEntity));
        when(programRepository.save(programEntity)).thenReturn(createdProgramEntity);
        ProgramDto createdProgramDto = programServiceImpl.createProgram(programDto);
        //then
        assertNotNull(createdProgramDto, "The created program should never be null");
        assertEquals(createdProgramDto.getProgramName(), "Java Internship");
        assertEquals(createdProgramDto.getStartDate(),  LocalDate.parse("2020-01-08"));
        assertEquals(createdProgramDto.getEndDate(),  LocalDate.parse("2020-01-15"));
    }

    @Test
    public void getPrograms() {
        when(programRepository.findAll()).thenReturn(programList());
        final List<ProgramDto> programsFound = programServiceImpl.findAllPrograms();
        assertNotNull(programsFound, "List is empty");
        assertEquals(programsFound.size(), 2, "List size doesn't match actual size");
        verify(programRepository).findAll();
    }

    @Test
    public void updateProgram() {
        CompetenceAreaEntity competenceAreaEntity = createCompetenceArea(2L);
        ProgramEntity programEntity = initializeProgram(1L,
                "Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                competenceAreaEntity);
        CompetenceAreaEntity updatedCompetenceArea = createCompetenceArea(1L);
        ProgramEntity updatedProgram = initializeProgram(programEntity.getId(),
                "Java Summer Internship",
                "hhh",
                LocalDate.parse("2020-07-08"),
                LocalDate.parse("2020-09-15"),
                updatedCompetenceArea);
        when(programRepository.findById(programEntity.getId())).thenReturn(Optional.of(programEntity));
        when(competenceAreaRepository.findById(updatedProgram.getCompetenceAreaEntity().getId())).thenReturn(Optional.of(updatedCompetenceArea));
        when(programRepository.save(programEntity)).thenReturn(updatedProgram);
        ProgramDto programDto = initializeProgramDto(updatedProgram.getId(),
                updatedProgram.getProgramName(),
                updatedProgram.getDescription(),
                updatedProgram.getStartDate(),
                updatedProgram.getEndDate(),
                updatedCompetenceArea.getId());
        ProgramDto updatedProgramDto = programServiceImpl.updateProgram(programDto, programDto.getId());
        assertNotNull(updatedProgramDto);
        assertEquals(updatedProgramDto.getProgramName(), "Java Summer Internship", "field name doesn't match");
    }

    @Test
    public void deleteProgram(){
        Long id = 3l;
        CompetenceAreaEntity competenceAreaEntity = TestUtils.createCompetenceArea(2l);
        UserEntity userEntity = TestUtils.createUserEntity("a@gmail.com", "a@gmail.com", "a@gmail.com", "a@gmail.com", "a@gmail.com");
        Set<UserEntity> userEntities = new HashSet<>();
        userEntities.add(userEntity);
        ProgramEntity programEntity = TestUtils.initializeProgramWithUser(3L,"Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                competenceAreaEntity,
                userEntities);
        when(programRepository.findById(id)).thenReturn(Optional.of(programEntity));
        Long idToDelete = 3l;
        programServiceImpl.deleteProgram(idToDelete);
        verify(programRepository, times(1)).deleteById(idToDelete);
    }

}