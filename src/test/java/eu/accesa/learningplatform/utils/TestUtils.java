package eu.accesa.learningplatform.utils;

import eu.accesa.learningplatform.model.dto.CourseDto;
import eu.accesa.learningplatform.model.dto.LessonDto;
import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.entity.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestUtils {
    public static LessonDto testLessonDto(Long id, String name, double duration, Long courseId) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(id);
        lessonDto.setName(name);
        lessonDto.setDuration(duration);
        lessonDto.setCourseId(courseId);
        return lessonDto;
    }

    public static List<LessonDto> testLessonDtoList() {
        return Arrays.asList(
                testLessonDto(1l, "java", 1.0, 1l),
                testLessonDto(2l, "java basic", 2.0, 1l));
    }

    public static LessonEntity testLessonEntity(Long id, String name, double duration, CourseEntity course) {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setId(id);
        lessonEntity.setName(name);
        lessonEntity.setDuration(duration);
        lessonEntity.setCourseEntity(course);
        return lessonEntity;
    }

    public static List<LessonEntity> testLessonList() {
        return Arrays.asList(
                testLessonEntity(1l, "java", 1l, testCourseEntity(1l, null, null,
                        null, null, null)),
                testLessonEntity(2l, "java basic", 2l, testCourseEntity(1l, null, null,
                        null, null, null)));
    }

    public static CourseEntity testCourseEntity(Long id, String name, String description, Double duration,
                                                ProgramEntity program, UserEntity user) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(id);
        courseEntity.setName(name);
        courseEntity.setDescription(description);
        courseEntity.setTotalDuration(duration);
        courseEntity.setProgramEntity(program);
        courseEntity.setUserEntity(user);
        return courseEntity;
    }

    public static List<CourseEntity> testCourseList() {
        return Arrays.asList(
                testCourseEntity(1l, "Spring Security", "Spring Security is a powerful..",
                        2.0, testProgramEntity(3l, null, null, null, null,
                                testCompetenceAreaEntity(1l, null)), testUserEntity(1l, null, null,
                                null, null, null, testCompetenceAreaEntity(1l, null) ,
                                testJobTitleEntity(1L, null), testUserTypeEntity(1L, null))),
                testCourseEntity(2l, "Spring Web", "Spring Web is ..", 3.0,
                        testProgramEntity(2l, null, null, null, null,
                                testCompetenceAreaEntity(2l, null)), testUserEntity(1l, null,
                                null, null, null, null, testCompetenceAreaEntity(1L, null),
                                testJobTitleEntity(1L, null), testUserTypeEntity(2l, null))));

    }

    public static ProgramEntity testProgramEntity(Long id, String programName, String description,
                                                  LocalDate startDate, LocalDate endDate,
                                                  CompetenceAreaEntity competenceAreaEntity) {
        ProgramEntity programEntity = new ProgramEntity();
        programEntity.setId(id);
        programEntity.setProgramName(programName);
        programEntity.setDescription(description);
        programEntity.setStartDate(startDate);
        programEntity.setEndDate(endDate);
        programEntity.setCompetenceAreaEntity(competenceAreaEntity);
        return programEntity;
    }

    public static CompetenceAreaEntity testCompetenceAreaEntity(Long id, CompetenceAreaEnum name) {
        CompetenceAreaEntity competenceAreaEntity = new CompetenceAreaEntity();
        competenceAreaEntity.setId(id);
        competenceAreaEntity.setName(name);
        return competenceAreaEntity;
    }

    public static UserEntity testUserEntity(Long id, String firstName, String lastName, String email,
                                            String imageUrl, String password, CompetenceAreaEntity competenceAreaEntity,
                                            JobTitleEntity jobTitleEntity, UserTypeEntity userTypeEntity) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setImageUrl(imageUrl);
        userEntity.setPassword(password);
        userEntity.setUserTypeEntity(userTypeEntity);
        userEntity.setJobTitleEntity(jobTitleEntity);
        userEntity.setCompetenceAreaEntity(competenceAreaEntity);
        return userEntity;
    }

    public static UserTypeEntity testUserTypeEntity(Long id, UserTypeEnum name) {
        UserTypeEntity userTypeEntity = new UserTypeEntity();
        userTypeEntity.setId(id);
        userTypeEntity.setName(name);
        return userTypeEntity;
    }

    public static UserDto testUserDto(Long id, String firstName, String lastName, String email, String imageUrl,
                                      String password, Long competenceAreaId, Long jobTitleId, Long userTypeID) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setImageUrl(imageUrl);
        userDto.setPassword(password);
        userDto.setCompetenceAreaId(competenceAreaId);
        userDto.setJobTitleId(jobTitleId);
        userDto.setUserTypeId(userTypeID);
        return userDto;
    }

    public static CourseDto testCourseDto(Long id, String name, String description, Double duration,
                                          Long programId, Long userId) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(id);
        courseDto.setName(name);
        courseDto.setDescription(description);
        courseDto.setTotalDuration(duration);
        courseDto.setProgramId(programId);
        courseDto.setUserId(userId);
        return courseDto;
    }

    public static ProgramDto testProgramDto(Long id, String programName, String description, LocalDate startDate,
                                            LocalDate endDate, Long competenceAreaId) {
        ProgramDto programDto = new ProgramDto();
        programDto.setId(id);
        programDto.setProgramName(programName);
        programDto.setDescription(description);
        programDto.setStartDate(startDate);
        programDto.setEndDate(endDate);
        programDto.setCompetenceAreaId(competenceAreaId);
        return programDto;
    }

    public static JobTitleEntity testJobTitleEntity(Long id, JobTitleEnum jobTitleEnum){
        JobTitleEntity jobTitleEntity = new JobTitleEntity();
        jobTitleEntity.setId(id);
        jobTitleEntity.setName(jobTitleEnum);
        return jobTitleEntity;
    }
}
