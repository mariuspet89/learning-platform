package eu.accesa.learningplatform.service.implementation;

import ch.qos.logback.classic.spi.LoggingEvent;
import eu.accesa.learningplatform.model.dto.AnswerDto;
import eu.accesa.learningplatform.model.entity.AnswerEntity;
import eu.accesa.learningplatform.model.entity.QuizItemEntity;
import eu.accesa.learningplatform.repository.AnswerRepository;
import eu.accesa.learningplatform.repository.QuizItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static eu.accesa.learningplatform.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class AnswerServiceImplTest {
    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuizItemRepository quizItemRepository;

    @Mock
    private Logger LOGGER = LoggerFactory.getLogger(LessonServiceImplTest.class);

    @Spy
    private ModelMapper mapper;
    @Captor
    private ArgumentCaptor<LoggingEvent> loadArgumentCaptor;

    @InjectMocks
    private AnswerServiceImpl answerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createAnswer(){
        // given
        QuizItemEntity quizItemEntity = testQuizItemEntity(1L, null, null, null, null);
        AnswerEntity answerEntity = testAnswerEntity(null, "aaa", true, quizItemEntity);
        AnswerEntity createdAnswerEntity = testAnswerEntity(1L, "aaa", true, quizItemEntity);
        AnswerDto answerDto = testAnswerDto(null, "aaa", true, 1L);

        // when
        when(quizItemRepository.findById(quizItemEntity.getId())).thenReturn(java.util.Optional.of(quizItemEntity));
        when(answerRepository.save(answerEntity)).thenReturn(createdAnswerEntity);
        AnswerDto createdAnswerDto = answerService.createAnswer(answerDto, quizItemEntity.getId());

        //then
        assertNotNull(createdAnswerDto, "The created answer should never be null");
        assertEquals(createdAnswerDto.getAnswerText(), "aaa");
        assertNotNull(createdAnswerDto.getId());
    }

    @Test
    public void delete(){
        Long id = 1L;
        QuizItemEntity quizItemEntity = testQuizItemEntity(1L, null, null, null, null);
        AnswerEntity foundAnswerEntity = testAnswerEntity(1L, "aaa", true, quizItemEntity);
        when(answerRepository.findById(id)).thenReturn(Optional.of(foundAnswerEntity));
        Long idToDelete = 1L;
        answerService.delete(idToDelete);
    }
}