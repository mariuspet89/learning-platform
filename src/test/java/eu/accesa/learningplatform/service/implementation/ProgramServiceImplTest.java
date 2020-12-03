package eu.accesa.learningplatform.service.implementation;

import ch.qos.logback.classic.spi.LoggingEvent;
import eu.accesa.learningplatform.model.dto.LessonDto;
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
import java.util.Optional;

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
    public void deleteProgram(){
        Long id = 3l;
        CompetenceAreaEntity competenceAreaEntity = TestUtils.createCompetenceArea(2l);
        ProgramEntity programEntity = TestUtils.initializeProgram("Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                competenceAreaEntity);
//        UserEntity userEntity = TestUtils.createUserEntity("a@gmail.com", "a@gmail.com", "a@gmail.com", "a@gmail.com", "a@gmail.com");
//        programEntity.getUserEntities().add(userEntity);
//        programEntity.getUserEntities().add(userEntity);
//        programEntity.getUserEntities().add(userEntity);
        when(programRepository.findById(id)).thenReturn(Optional.of(programEntity));
        Long idToDelete = 3l;
        programServiceImpl.deleteProgram(idToDelete);
        verify(programRepository, times(1)).deleteById(idToDelete);
    }

}