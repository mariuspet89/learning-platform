package eu.accesa.learningplatform.utils;

import eu.accesa.learningplatform.model.dto.*;
import eu.accesa.learningplatform.model.entity.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestUtils {
    public static LessonContentEntity testLessonContentEntity(Long Id, String content, LessonEntity lesson){
        LessonContentEntity lessonContentEntity = new LessonContentEntity();
        lessonContentEntity.setId(Id);
        lessonContentEntity.setContent(content);
        lessonContentEntity.setLessonEntity(lesson);
        return lessonContentEntity;
    }

    public static LessonContentDto testLessonContentDto(Long Id, String content, Long lessonId){
        LessonContentDto lessonContentDto = new LessonContentDto();
        lessonContentDto.setId(Id);
        lessonContentDto.setContent(content);
        lessonContentDto.setLessonId(lessonId);
        return lessonContentDto;
    }

    public static List<LessonContentEntity>testLessonContentList(){
        LessonEntity lessonEntity =  testLessonEntity(1L, null, 2.5, null);
        return Arrays.asList(
                testLessonContentEntity(1L, "The best content", lessonEntity),
                testLessonContentEntity(2L, "The best Java content", lessonEntity));
    }


    public static LessonDto testLessonDto(Long Id, String name, double duration, Long courseId) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(Id);
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
                                null, null, null, null, null,
                                testCompetenceAreaEntity(1l, null))),
                testCourseEntity(2l, "Spring Web", "Spring Web is ..", 3.0,
                        testProgramEntity(2l, null, null, null, null,
                                testCompetenceAreaEntity(2l, null)), testUserEntity(1l, null,
                                null, null, null, null, null,
                                null, testCompetenceAreaEntity(2l, null))));

    }

    public static List<ProgramEntity> testProgramList() {
        return Arrays.asList(
                testProgramEntity(1L,
                        "Java Internship",
                        "aa",
                        LocalDate.parse("2020-01-08"),
                        LocalDate.parse("2020-01-15"),
                        testCompetenceAreaEntity(1L, CompetenceAreaEnum.JAVA_TECHNOLOGY)),
                testProgramEntity(2L,
                        "AI Internship",
                        "bb",
                        LocalDate.parse("2020-01-10"),
                        LocalDate.parse("2020-05-15"),
                        testCompetenceAreaEntity(2L, CompetenceAreaEnum.PRODUCT_DESIGN))
        );
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
                                            String imageUrl, String password, UserTypeEntity userTypeEntity,
                                            JobTitleEntity jobTitleEntity, CompetenceAreaEntity competenceAreaEntity) {
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

    public static UserEntity testUserEntity(String firstName, String lastName, String email,
                                            String imageUrl, String password) {
        ProgramEntity programEntity = testProgramEntity(3L, "Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                testCompetenceAreaEntity(1L, CompetenceAreaEnum.JAVA_TECHNOLOGY));

        Set<ProgramEntity> programEntities = new HashSet<>();
        programEntities.add(programEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setImageUrl(imageUrl);
        userEntity.setEmail(email);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setPassword(password);
        userEntity.setProgramEntities(programEntities);
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
        //  userDto.setJobTitleId(jobTitleId);
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

    public static ApplicationEntity testApplicationEntity(Long id, ApplicationStatusEnum statusEnum, String courseIdea, UserEntity userEntity) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setId(id);
        applicationEntity.setStatus(statusEnum);
        applicationEntity.setCourseIdea(courseIdea);
        applicationEntity.setUserEntity(userEntity);
        return applicationEntity;
    }

    public static ApplicationDto testApplicationDto(Long id, ApplicationStatusEnum statusEnum, String courseIdea, Long userEntityId) {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(id);
        applicationDto.setStatus(statusEnum);
        applicationDto.setCourseIdea(courseIdea);
        applicationDto.setUserEntityId(userEntityId);
        return applicationDto;
    }

    public static List<ApplicationEntity> testApplicationList() {
        return Arrays.asList(
                testApplicationEntity(null, ApplicationStatusEnum.PENDING, "gitTraining",
                        testUserEntity(1L, null, null, null, null, null,
                                null, null, null)),
                testApplicationEntity(null, ApplicationStatusEnum.PENDING, "javaTraining",
                        testUserEntity(2L, null, null, null, null, null,
                                null, null, null)));
    }

    public static List<ApplicationEntity> testApplicationListSameUser() {
        return Arrays.asList(
                testApplicationEntity(null, ApplicationStatusEnum.PENDING, "gitTraining",
                        testUserEntity(1L, null, null, null, null, null,
                                null, null, null)),
                testApplicationEntity(null, ApplicationStatusEnum.PENDING, "javaTraining",
                        testUserEntity(1L, null, null, null, null, null,
                                null, null, null)));
    }

    public static ProgramEntity testProgramWithUser(Long id, String name, String desc, LocalDate startDate, LocalDate endDate, CompetenceAreaEntity competenceAreaEntity, Set<UserEntity> userEntities) {
        ProgramEntity programEntity = new ProgramEntity();
        programEntity.setId(id);
        programEntity.setProgramName(name);
        programEntity.setDescription(desc);
        programEntity.setStartDate(startDate);
        programEntity.setEndDate(endDate);
        programEntity.setCompetenceAreaEntity(competenceAreaEntity);
        programEntity.setUserEntities(userEntities);
        return programEntity;
    }

}
