package eu.accesa.learningplatform.service.implementation;


import eu.accesa.learningplatform.model.dto.LessonDto;
import eu.accesa.learningplatform.model.entity.CourseEntity;
import eu.accesa.learningplatform.model.entity.LessonEntity;
import eu.accesa.learningplatform.repository.CourseRepository;
import eu.accesa.learningplatform.repository.LessonRepository;
import eu.accesa.learningplatform.service.LessonService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class LessonServiceImpl implements LessonService {


    private static final Logger LOGGER = LoggerFactory.getLogger(LessonServiceImpl.class);
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final ModelMapper mapper;

    @Autowired
    public LessonServiceImpl(CourseRepository courseRepository, LessonRepository lessonRepository, ModelMapper mapper) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.mapper = mapper;
    }

    @Override
    public LessonDto createLesson(LessonDto lessonDto) {
        LOGGER.info("Service: creating lesson with values: {}", lessonDto);
        CourseEntity courseEntity = courseRepository.findById(lessonDto.getCourseId()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course wit id " + lessonDto.getCourseId() + " not found"));
        LessonEntity lesson = mapper.map(lessonDto, LessonEntity.class);
        lesson.setCourseEntity(courseEntity);
        lessonRepository.save(lesson);
        return mapper.map(lesson, LessonDto.class);
    }

    @Override
    public LessonDto getLessonById(Long id) {
        LOGGER.info("Service: retrieving lesson with id: {}", id);
        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson with id " + id + " not found"));
        return mapper.map(lessonEntity, LessonDto.class);
    }

    @Override
    public List<LessonDto> getLessonsByCourse(Long courseId) {
        LOGGER.info("Service: retrieving all lessons for a course with id: {}", courseId);
        List<LessonEntity> lessonEntities = lessonRepository.findByCourseEntity_Id(courseId);
        return mapper.map(lessonEntities, new TypeToken<List<LessonDto>>() {
        }.getType());
    }

    @Override
    public LessonDto updateLesson(LessonDto lessonDto) {
        LOGGER.info("Lesson: updating lesson with id: {}, with values: {}", lessonDto.getId(), lessonDto);

        LessonEntity lessonEntity = lessonRepository.findById(lessonDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson with id " + lessonDto.getId() + " not found"));

        mapper.map(lessonDto, lessonEntity);

        return mapper.map(lessonEntity, LessonDto.class);
    }

    @Override
    public void deleteLesson(Long id) {
        LOGGER.info("Lesson: deleting the lesson with id: {} ", id);

        LessonEntity lessonEntity = lessonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson with id " + id + " not found"));
        lessonRepository.delete(lessonEntity);

    }
}
