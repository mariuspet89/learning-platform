package eu.accesa.learningplatform.service.implementation;

import ch.qos.logback.classic.spi.LoggingEvent;
import eu.accesa.learningplatform.repository.CourseRepository;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProgramRepository programRepository;
    @Mock
    private Logger LOGGER = LoggerFactory.getLogger(CourseServiceImplTest.class);
    @Spy
    private ModelMapper mapper;
    @Captor
    private ArgumentCaptor<LoggingEvent> loadArgumentCaptor;
    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addNewCourse() {
    }

    @Test
    void getCourseById() {
    }

    @Test
    void getCoursesByNameContainingKeyword() {
    }

    @Test
    void getCoursesByTrainer() {
    }

    @Test
    void getAllCourses() {
    }

    @Test
    void getAllCoursesByProgramId() {
    }

    @Test
    void updateCourse() {
    }

    @Test
    void deleteCourseById() {
    }
}