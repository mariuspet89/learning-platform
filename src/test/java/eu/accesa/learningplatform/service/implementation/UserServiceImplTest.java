package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.entity.*;
import eu.accesa.learningplatform.repository.CompetenceAreaRepository;
import eu.accesa.learningplatform.repository.JobTitleRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import eu.accesa.learningplatform.repository.UserTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.LoggingEvent;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.WeakHashMap;

import static eu.accesa.learningplatform.utils.TestUtils.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CompetenceAreaRepository competenceAreaRepository;
    @Mock
    private JobTitleRepository jobTitleRepository;
    @Mock
    private UserTypeRepository userTypeRepository;
    @Mock
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImplTest.class);
    @Spy
    private ModelMapper mapper;
    @Captor
    private ArgumentCaptor<LoggingEvent> loadArgumentCaptor;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUser() {
        //given
        CompetenceAreaEntity competenceAreaEntity = testCompetenceAreaEntity(1L, CompetenceAreaEnum.JAVA_TECHNOLOGY);
        JobTitleEntity jobTitleEntity = testJobTitleEntity(1L, JobTitleEnum.JAVA_DEVELOPER);
        UserTypeEntity userTypeEntity = testUserTypeEntity(1L, UserTypeEnum.TRAINEE);
        UserEntity userEntity = testUserEntity(null, "first", "last", "mail@email.com",
                null, "strongp4ssw0rd", competenceAreaEntity, jobTitleEntity, userTypeEntity);
        UserEntity createdUser = testUserEntity(null, "first", "last", "mail@email.com",
                null, "strongp4ssw0rd", competenceAreaEntity, jobTitleEntity, userTypeEntity);
        UserDto userDto = testUserDto(null, "first", "last", "mail@email.com",
                null, "strongp4ssw0rd", 1L, 1L, 1L);

        when(competenceAreaRepository.findById(competenceAreaEntity.getId())).thenReturn(Optional.of(competenceAreaEntity));
        when(jobTitleRepository.findById(jobTitleEntity.getId())).thenReturn(Optional.of(jobTitleEntity));
        when(userTypeRepository.findById(userTypeEntity.getId())).thenReturn(Optional.of(userTypeEntity));
        //when

        //then

    }

    @Test
    public void getAllUsers() {

    }

    @Test
    public void getAllUsersByProgram() {

    }

    @Test
    public void getUserById() {

    }

    @Test
    public void getUsersByUserType() {

    }

    @Test
    public void updateUser() {

    }

    public void deleteUser() {

    }
}
