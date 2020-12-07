package eu.accesa.learningplatform.service.implementation;

import ch.qos.logback.classic.spi.LoggingEvent;
import eu.accesa.learningplatform.model.dto.RatingDto;
import eu.accesa.learningplatform.model.entity.CourseEntity;
import eu.accesa.learningplatform.model.entity.RatingEntity;
import eu.accesa.learningplatform.model.entity.UserEntity;
import eu.accesa.learningplatform.repository.CourseRepository;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.repository.RatingRepository;
import eu.accesa.learningplatform.repository.UserRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
class RatingServiceImplTest {
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveRating() {
        //given
        CourseEntity courseEntity = testCourseEntity(1L, "course", "good", 3.0, null, null);
        UserEntity userEntity = testUserEntity(1L, "cristina", "popescu", "cristina.popescu97@yahoo.com", null, "c", null, null, null);

        RatingEntity ratingEntity = testRatingEntity(null, 4, "good", userEntity, courseEntity);
        RatingEntity createdRatingEntity = testRatingEntity(1L, 4, "good", userEntity, courseEntity);

        RatingDto ratingDto = testRatingDto(null, 4, "good", 1L, 1L);

        // when
        when(courseRepository.findById(ratingEntity.getCourseEntity().getId())).thenReturn(Optional.of(courseEntity));
        when(userRepository.findById(ratingEntity.getUserEntity().getId())).thenReturn(Optional.of(userEntity));
        when(ratingRepository.save(ratingEntity)).thenReturn(createdRatingEntity);
        RatingDto createdRatingDto = ratingService.createRating(ratingDto);

        assertNotNull(createdRatingDto, "The created rating should never be null");
        assertEquals(createdRatingDto.getDescription(), "good");
        assertEquals(createdRatingDto.getNoOfStars(), 4);
    }

    @Test
    public void getRatingByCourse() {

    }

    @Test
    public void updateRating() {

    }

    @Test
    public void deleteRating() {
        CourseEntity courseEntity = testCourseEntity(1L, "course", "good", 3.0, null, null);
        UserEntity userEntity = testUserEntity(1L, "cristina", "popescu", "cristina.popescu97@yahoo.com", null, "c", null, null, null);

        RatingEntity ratingEntity = testRatingEntity(null, 4, "good", userEntity, courseEntity);

        when(ratingRepository.findById(1L)).thenReturn(Optional.of(ratingEntity));
        ratingService.deleteRating(1L);
    }

}
