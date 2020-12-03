package eu.accesa.learningplatform.utils;

import eu.accesa.learningplatform.model.dto.LessonDto;
import eu.accesa.learningplatform.model.dto.ProgramDto;
import eu.accesa.learningplatform.model.entity.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestUtils {
    public static LessonDto testUtilsLessonDto(Long id, String name, double duration, Long courseId) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(id);
        lessonDto.setName(name);
        lessonDto.setDuration(duration);
        lessonDto.setCourseId(courseId);
        return lessonDto;
    }

    public static LessonDto testUtilsLessonDtoNoId(String name, double duration, Long courseId) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setName(name);
        lessonDto.setDuration(duration);
        lessonDto.setCourseId(courseId);
        return lessonDto;
    }

    public static List<LessonDto> testUtilsLessonDtoList() {
        return Arrays.asList(
                testUtilsLessonDto(1l, "java", 1.0, 1l),
                testUtilsLessonDto(2l, "java basic", 2.0, 1l));
    }

    public static LessonEntity testUtilsLessonEntity(Long id, String name, double duration, CourseEntity course) {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setId(id);
        lessonEntity.setName(name);
        lessonEntity.setDuration(duration);
        lessonEntity.setCourseEntity(course);
        return lessonEntity;
    }

    public static LessonEntity testUtilsLessonEntityNoId(String name, double duration, CourseEntity course) {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setName(name);
        lessonEntity.setDuration(duration);
        lessonEntity.setCourseEntity(course);
        return lessonEntity;
    }

    public static CourseEntity testUtilsCourseEntity(Long id) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(id);
        return courseEntity;
    }

    public static List<LessonEntity> testUtilsLessonList() {
        return Arrays.asList(
                testUtilsLessonEntity(1l, "java", 1l, testUtilsCourseEntity(1l)),
                testUtilsLessonEntity(2l, "java basic", 2l, testUtilsCourseEntity(1l)));
    }

    public static ProgramEntity initializeProgram(String name, String desc, LocalDate startDate, LocalDate endDate, CompetenceAreaEntity competenceAreaEntity){
        ProgramEntity programEntity = new ProgramEntity();
        programEntity.setProgramName(name);
        programEntity.setDescription(desc);
        programEntity.setStartDate(startDate);
        programEntity.setEndDate(endDate);
        programEntity.setCompetenceAreaEntity(competenceAreaEntity);
        return programEntity;
    }

    public static ProgramEntity initializeProgram(Long id, String name, String desc, LocalDate startDate, LocalDate endDate, CompetenceAreaEntity competenceAreaEntity){
        ProgramEntity programEntity = new ProgramEntity();
        programEntity.setId(id);
        programEntity.setProgramName(name);
        programEntity.setDescription(desc);
        programEntity.setStartDate(startDate);
        programEntity.setEndDate(endDate);
        programEntity.setCompetenceAreaEntity(competenceAreaEntity);
        return programEntity;
    }

    public static ProgramEntity initializeProgramWithUser(Long id, String name, String desc, LocalDate startDate, LocalDate endDate, CompetenceAreaEntity competenceAreaEntity, Set<UserEntity> userEntities){
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

    public static ProgramDto initializeProgramDto(String name, String desc, LocalDate startDate, LocalDate endDate, Long cmpAreaId){
        ProgramDto programDto = new ProgramDto();
        programDto.setProgramName(name);
        programDto.setDescription(desc);
        programDto.setStartDate(startDate);
        programDto.setEndDate(endDate);
        programDto.setCompetenceAreaId(cmpAreaId);
        return programDto;
    }

    public static ProgramDto initializeProgramDto(Long id, String name, String desc, LocalDate startDate, LocalDate endDate, Long cmpAreaId){
        ProgramDto programDto = new ProgramDto();
        programDto.setId(id);
        programDto.setProgramName(name);
        programDto.setDescription(desc);
        programDto.setStartDate(startDate);
        programDto.setEndDate(endDate);
        programDto.setCompetenceAreaId(cmpAreaId);
        return programDto;
    }

    public static CompetenceAreaEntity createCompetenceArea(Long id){
        CompetenceAreaEntity competenceAreaEntity = new CompetenceAreaEntity();
        competenceAreaEntity.setId(id);
        return competenceAreaEntity;
    }

    public static UserEntity createUserEntity(String a, String b, String c, String d, String e){
        ProgramEntity programEntity = initializeProgram(3L,"Java Internship",
                "aa",
                LocalDate.parse("2020-01-08"),
                LocalDate.parse("2020-01-15"),
                createCompetenceArea(1L));
        Set<ProgramEntity> programEntities = new HashSet<>();
        programEntities.add(programEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setImageUrl(a);
        userEntity.setEmail(b);
        userEntity.setFirstName(c);
        userEntity.setLastName(d);
        userEntity.setPassword(e);
        userEntity.setProgramEntities(programEntities);
        return userEntity;
    }

    public static List<ProgramEntity> programList() {
        return Arrays.asList(
                initializeProgram(1L,
                        "Java Internship",
                        "aa",
                        LocalDate.parse("2020-01-08"),
                        LocalDate.parse("2020-01-15"),
                        createCompetenceArea(1L)),
                initializeProgram(2L,
                        "AI Internship",
                        "bb",
                        LocalDate.parse("2020-01-10"),
                        LocalDate.parse("2020-05-15"),
                        createCompetenceArea(1L))
                );

    }
}
