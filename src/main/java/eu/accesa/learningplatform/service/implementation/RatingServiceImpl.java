package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.RatingDto;
import eu.accesa.learningplatform.model.entity.CourseEntity;
import eu.accesa.learningplatform.model.entity.RatingEntity;
import eu.accesa.learningplatform.model.entity.UserEntity;
import eu.accesa.learningplatform.repository.CourseRepository;
import eu.accesa.learningplatform.repository.RatingRepository;
import eu.accesa.learningplatform.repository.UserRepository;
import eu.accesa.learningplatform.service.RatingService;
import eu.accesa.learningplatform.service.exception.LearningPlatformException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RatingServiceImpl implements RatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingServiceImpl.class);

    private final RatingRepository ratingRepository;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    public RatingServiceImpl(RatingRepository ratingRepository,
                             UserRepository userRepository,
                             CourseRepository courseRepository,
                             ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RatingDto createRating(RatingDto ratingDto) {

        LOGGER.info("Creating Rating " + ratingDto.getId());

        RatingEntity ratingEntity = modelMapper.map(ratingDto, RatingEntity.class);

        UserEntity userEntity = userRepository.findById(ratingDto.getUserId())
                .orElseThrow();//arunca o exceptie;

        CourseEntity courseEntity = courseRepository.findById(ratingDto.getCourseId())
                .orElseThrow();//arunca o exceptie;

        ratingEntity.setUserEntity(userEntity);

        ratingEntity.setCourseEntity(courseEntity);

        return modelMapper.map(ratingRepository.save(ratingEntity), RatingDto.class);
    }

    @Override
    public List<RatingDto> getAllRatingsByCourseId(Long id) {
        LOGGER.info("Getting all ratings having course id: " + id);

        CourseEntity courseEntity = courseRepository.findById(id)
                .orElseThrow(()
                        -> new LearningPlatformException("Course not found with the following id: " + id));

        List<RatingEntity> ratingEntities = ratingRepository.findAllByCourseEntity_Id(id);

        return ratingEntities.stream()
                .map(rating -> modelMapper.map(rating, RatingDto.class))
                .collect(toList());
    }

    @Override
    public RatingDto updateRating(RatingDto ratingDto) {
        LOGGER.info("Updating rating " + ratingDto.getId());

        RatingEntity ratingEntity = ratingRepository.findById(ratingDto.getId())
                .orElseThrow(()
                        -> new LearningPlatformException("Rating not found with the following id: " + ratingDto.getId()));

        if (ratingEntity.getCourseEntity().getId().equals(ratingDto.getCourseId())) {
            throw new LearningPlatformException("You can't update Course id.");
        }

        modelMapper.map(ratingDto, ratingEntity);

        ratingRepository.save(ratingEntity);

        return modelMapper.map(ratingEntity, RatingDto.class);
    }

    @Override
    public void deleteRating(Long id) {
        LOGGER.info("Deleting rating with the following ID: " + id);

        RatingEntity ratingEntity = ratingRepository.findById(id).orElseThrow(()
                -> new LearningPlatformException("Rating Not Found with the following ID:" + id));
        ratingRepository.delete(ratingEntity);

    }
}
