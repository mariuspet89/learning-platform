package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.entity.CourseEntity;
import eu.accesa.learningplatform.repository.CourseRepository;
import eu.accesa.learningplatform.repository.RatingRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.springframework.test.context.junit4.SpringRunner;

import static eu.accesa.learningplatform.utils.TestUtils.*;


@RunWith(SpringRunner.class)
public class RatingServiceImplTest {
    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private Logger LOGGER = LoggerFactory.getLogger(LessonServiceImplTest.class);

    @Spy
    private ModelMapper mapper;
    @Captor
    private ArgumentCaptor<LoggingEvent> loadArgumentCaptor;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Test
    public void saveRating(){
    }

    @Test
    public void getRatingByCourse(){

    }

    @Test
    public void updateRating(){

    }

    @Test
    public void deleteRating(){

    }

}
