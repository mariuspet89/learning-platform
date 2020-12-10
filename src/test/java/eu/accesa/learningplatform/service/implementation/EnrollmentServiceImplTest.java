package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.EnrollmentDto;
import eu.accesa.learningplatform.model.entity.CompetenceAreaEntity;
import eu.accesa.learningplatform.model.entity.ProgramEntity;
import eu.accesa.learningplatform.model.entity.UserEntity;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.LoggingEvent;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static eu.accesa.learningplatform.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class EnrollmentServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ProgramRepository programRepository;
    @Mock
    private Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImplTest.class);

    @Spy
    private ModelMapper mapper;
    @Captor
    private ArgumentCaptor<LoggingEvent> loadArgumentCaptor;
    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void enroll() {

        LocalDate startDate = LocalDate.of(2020, 12, 1);
        LocalDate endDate = LocalDate.of(2020, 12, 7);

        Set<UserEntity> userEntities = new HashSet<>();

        CompetenceAreaEntity competenceAreaEntity =
                testCompetenceAreaEntity(1L, null);

        ProgramEntity programEntity = testProgramWithUser(1L,
                "test", "dest", startDate, endDate, competenceAreaEntity, userEntities);

        ProgramEntity programEntityUpdated = testProgramWithUser(1L,
                "test", "dest", startDate, endDate, competenceAreaEntity, userEntities);

        UserEntity userEntity1 = testUserEntity("test", "test", null, null, null);

        EnrollmentDto enrollmentDto = testEnrollmentDto(userEntity1.getId(), programEntity.getId());

        when(userRepository.findById(enrollmentDto.getUserId())).thenReturn(Optional.of(userEntity1));
        when(programRepository.findById(enrollmentDto.getProgramId())).thenReturn(Optional.of(programEntity));
        when(programRepository.findById(enrollmentDto.getProgramId())).thenReturn(Optional.of(programEntityUpdated));

        programEntityUpdated.getUserEntities().add(userEntity1);
        userEntity1.getProgramEntities().add(programEntityUpdated);

        when(programRepository.save(programEntity)).thenReturn(programEntityUpdated);

        EnrollmentDto enrollmentCreateDto = enrollmentService.enroll(enrollmentDto);

        assertEquals(enrollmentCreateDto.getUserId(), 1L);
        verify(userRepository).findById(enrollmentDto.getUserId());


    }

    @Test
    public void undoEnroll() {
    }

    @Test
    public void getAllEnrollments() {
    }
}