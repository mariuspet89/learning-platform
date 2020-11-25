package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.CourseDto;
import eu.accesa.learningplatform.model.entity.CourseEntity;
import eu.accesa.learningplatform.model.entity.ProgramEntity;
import eu.accesa.learningplatform.model.entity.UserEntity;
import eu.accesa.learningplatform.model.entity.UserTypeEnum;
import eu.accesa.learningplatform.repository.CourseRepository;
import eu.accesa.learningplatform.repository.ProgramRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import eu.accesa.learningplatform.service.CourseService;
import eu.accesa.learningplatform.service.exception.EntityNotFoundException;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository,
                             ProgramRepository programRepository,
                             ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto addNewCourse(CourseDto courseDto) {
        LOGGER.info("Creating course: {}", courseDto);
        CourseEntity courseEntity = modelMapper.map(courseDto, CourseEntity.class);
        ProgramEntity programEntity = programRepository.findById(courseDto.getProgramId())
                .orElseThrow(() ->
                        new EntityNotFoundException("ProgramEntity", "id", courseDto.getProgramId().toString()));
        UserEntity userEntity = userRepository.findById(courseDto.getUserId())
                .orElseThrow(() ->
                        new EntityNotFoundException("UserEntity", "id", courseDto.getUserId().toString()));
        courseEntity.setUserEntity(userEntity);
        courseEntity.setProgramEntity(programEntity);
        return modelMapper.map(courseRepository.save(courseEntity), CourseDto.class);
    }

    @Override
    public CourseDto getCourseById(Long id) {
        LOGGER.info("Searching for course with id: {}", id);
        return modelMapper.map(courseRepository.findById(id).
                orElseThrow(() ->
                        new EntityNotFoundException("CourseEntity", "id", id.toString())), CourseDto.class);
    }

    @Override
    public List<CourseDto> getCoursesByNameContainingKeyword(String name) {
        LOGGER.info("Searching for course by a keyword: {}", name);
        List<CourseEntity> courseEntities = courseRepository.findByNameContaining(name);
        return modelMapper.map(courseEntities, new TypeToken<List<CourseDto>>() {
        }.getType());
    }

    @Override
    public List<CourseDto> getCoursesByTrainer(Long id) {
        LOGGER.info("Searching for course by trainer with id: {}", id);
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("UserEntity", "id", id.toString()));
        if (!UserTypeEnum.TRAINER.equals(userEntity.getUserTypeEntity().getName()))
            return new LinkedList<>();
        List<CourseEntity> courseEntities = courseRepository.findByUserEntity_Id(id);
        return modelMapper.map(courseEntities, new TypeToken<List<CourseDto>>() {
        }.getType());
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto) {
        LOGGER.info("Updating course with id: {}", courseDto.getId());
        CourseEntity courseEntity = courseRepository.findById(courseDto.getId()).orElseThrow(() ->
                new EntityNotFoundException("CourseEntity", "id", courseDto.getId().toString()));
        modelMapper.map(courseDto, courseEntity);
        ProgramEntity programEntity = programRepository.findById(courseDto.getProgramId())
                .orElseThrow(() ->
                        new EntityNotFoundException("ProgramEntity", "id", courseDto.getProgramId().toString()));
        UserEntity userEntity = userRepository.findById(courseDto.getUserId())
                .orElseThrow(() ->
                        new EntityNotFoundException("UserEntity", "id", courseDto.getUserId().toString()));
        courseEntity.setUserEntity(userEntity);
        courseEntity.setProgramEntity(programEntity);
        return modelMapper.map(courseRepository.save(courseEntity), CourseDto.class);
    }


    @Override
    public void deleteCourseById(Long id) {
        LOGGER.info("Deleting course with id: {} ", id);
        courseRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("CourseEntity", "id", id.toString()));
        courseRepository.deleteById(id);
    }
}
