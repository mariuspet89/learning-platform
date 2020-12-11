package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.CourseDto;
import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.dto.UserDtoForGetCalls;
import eu.accesa.learningplatform.model.entity.*;
import eu.accesa.learningplatform.repository.*;
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
import java.util.List;
import java.util.Optional;


import static eu.accesa.learningplatform.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CompetenceAreaRepository competenceAreaRepository;
    @Mock
    private JobTitleRepository jobTitleRepository;
    @Mock
    private UserTypeRepository userTypeRepository;
    @Mock
    private ProgramRepository programRepository;
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
                null, "strongp4ssw0rd", userTypeEntity, jobTitleEntity, competenceAreaEntity);
        UserEntity createdUser = testUserEntity(1L, "first", "last", "mail@email.com",
                null, "strongp4ssw0rd", userTypeEntity, jobTitleEntity, competenceAreaEntity);
        UserDto userDto = testUserDto(null, "first", "last", "mail@email.com",
                null, "strongp4ssw0rd", 1L, 1L, 1L);

        // when
        when(competenceAreaRepository.findById(competenceAreaEntity.getId())).thenReturn(Optional.of(competenceAreaEntity));
        when(jobTitleRepository.findById(jobTitleEntity.getId())).thenReturn(Optional.of(jobTitleEntity));
        when(userTypeRepository.findById(userTypeEntity.getId())).thenReturn(Optional.of(userTypeEntity));
        when(userRepository.save(userEntity)).thenReturn(createdUser);
        UserDto createdUserDto = userServiceImpl.createUser(userDto);

        //then
        assertNotNull(createdUserDto, "Created user must not be null");
        assertNotNull(createdUserDto.getId(), "Created user's id must not be null");
        assertEquals(createdUserDto.getFirstName(), "first", "First names do not match");
        assertEquals(createdUserDto.getLastName(), "last", "Last names do not match");
        assertEquals(createdUserDto.getEmail(), "mail@email.com", "Emails do not match");
        assertEquals(createdUserDto.getPassword(), "strongp4ssw0rd", "Passwords do not match");
        assertEquals(createdUserDto.getCompetenceAreaId(), 1L, "CompetenceAreaId not valid");
        assertEquals(createdUserDto.getJobTitleId(), 1L, "JobTitleId not valid");
        assertEquals(createdUserDto.getUserTypeId(), 1L, "UserTypeId not valid");
    }

    @Test
    public void getAllUsers() {
        when(userRepository.findAll()).thenReturn(testUserEntityList());
        final List<UserDtoForGetCalls> usersFound = userServiceImpl.getAllUsers();
        assertNotNull(usersFound, "List is empty");
        assertEquals(usersFound.size(), 2, "List size doesn't match actual size");
        verify(userRepository).findAll();
    }

    @Test
    public void getAllUsersByProgram() {
        Long programId = 1L;
        ProgramEntity programEntity = testProgramEntity(1L,
                "Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                null);
        when(programRepository.findById(programId)).thenReturn(Optional.of(programEntity));
        when(userRepository.findAllByProgramEntities_Id(programId)).thenReturn(testUserEntityList());
        final List<UserDtoForGetCalls> userDtoForGetCallsList = userServiceImpl.getAllUsersByProgram(programId);
        assertNotNull(userDtoForGetCallsList, "List is empty");
        assertEquals(userDtoForGetCallsList.size(), 2, "List size doesn't match actual size");
        UserDtoForGetCalls userDtoForGetCalls = userDtoForGetCallsList.get(0);
        assertNotNull(userDtoForGetCalls.getId(), "Id should not be null");
        assertEquals(userDtoForGetCalls.getFirstName(), "first", "First names do not match");
        assertEquals(userDtoForGetCalls.getLastName(), "last", "Last names do not match");
        assertEquals(userDtoForGetCalls.getEmail(), "mail@email.com", "Emails do not match");
        assertEquals(userDtoForGetCalls.getPassword(), "strongp4ssw0rd", "Passwords do not match");
        verify(userRepository).findAllByProgramEntities_Id(programId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void getUserById() {
        UserEntity foundUserEntity =  testUserEntity(1L, "first", "last", "mail@email.com",
                null, "strongp4ssw0rd", null, null, null);
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(foundUserEntity));
        UserDtoForGetCalls userDtoForGetCalls = userServiceImpl.getUserById(id);
        assertEquals(userDtoForGetCalls.getId(), id, "ID mismatch");
        verify(userRepository).findById(foundUserEntity.getId());
        verifyNoMoreInteractions(userRepository);
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
