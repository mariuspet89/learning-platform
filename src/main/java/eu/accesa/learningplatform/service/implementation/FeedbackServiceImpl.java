package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.exceptionhandler.EmptyFieldsException;
import eu.accesa.learningplatform.exceptionhandler.LearningPlatformException;
import eu.accesa.learningplatform.model.dto.FeedbackDto;
import eu.accesa.learningplatform.model.entity.FeedbackEntity;
import eu.accesa.learningplatform.model.entity.LessonEntity;
import eu.accesa.learningplatform.repository.FeedbackRepository;
import eu.accesa.learningplatform.repository.LessonRepository;
import eu.accesa.learningplatform.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FeedbackServiceImpl implements FeedbackService {


    private final FeedbackRepository feedbackRepository;

    private final ModelMapper modelMapper;

    private final LessonRepository lessonRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);


    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ModelMapper modelMapper, LessonRepository lessonRepository) {
        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) throws LearningPlatformException, EmptyFieldsException {

        LOGGER.info("Creating Feedback " + feedbackDto.getId());

        List<LessonEntity> lessonEntities = lessonRepository.findAll();
        List<Long> lessonsIds = new ArrayList<>();

        for (LessonEntity l : lessonEntities) {
            lessonsIds.add(l.getId());
        }

        if (!lessonsIds.contains(feedbackDto.getLessonEntity().getId())) {
            throw new LearningPlatformException(
                    "Lesson Not Found with the following ID: " +
                            feedbackDto.getLessonEntity().getId());
        }

        FeedbackEntity feedbackEntity = feedbackRepository
                .save(modelMapper.map(feedbackDto, FeedbackEntity.class));

        int a = 0;
        if (feedbackEntity.getDescription().isEmpty() && feedbackEntity.getTitle().isEmpty()) {
            a = 1;
        }
        if (feedbackEntity.getTitle().isEmpty() && !feedbackEntity.getDescription().isEmpty()) {
            a = 2;
        }
        if (feedbackEntity.getDescription().isEmpty() && !feedbackEntity.getTitle().isEmpty()) {
            a = 3;
        }
        switch (a) {
            case 1:
                throw new EmptyFieldsException("Empty Title and Description");
            case 2:
                throw new EmptyFieldsException("Empty Title Field");
            case 3:
                throw new EmptyFieldsException("Empty Description Field");
        }


        return modelMapper.map(feedbackEntity, FeedbackDto.class);
    }

    @Override
    public List<FeedbackDto> findFeedbackEntityByLesson_Id(Long id) throws LearningPlatformException {

        LOGGER.info("Searching for the feedbacks related to the lesson with the ID " + id);

        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(()
                -> new LearningPlatformException("Lesson Not Found with the following ID:" + id));
        List<FeedbackEntity> feedbackEntities = feedbackRepository.findAllByLessonEntity_Id(id);

        return feedbackEntities.stream()
                .map(feedback -> modelMapper.map(feedback, FeedbackDto.class))
                .collect(toList());
    }

    @Override
    public FeedbackDto findFeedbackById(Long Id) throws LearningPlatformException {
        LOGGER.info("Searching for the Feedback with the following ID: " + Id);
        FeedbackEntity feedbackEntity = feedbackRepository.findById(Id)
                .orElseThrow(()
                        -> new LearningPlatformException("Feedback Not Found with the following ID:" + Id));
        return modelMapper.map(feedbackEntity, FeedbackDto.class);
    }

    @Override
    public FeedbackDto updateFeedback(FeedbackDto feedbackDto)
            throws LearningPlatformException {
        LOGGER.info("Updating Feedback " + feedbackDto.getId());
        ModelMapper mapperForCreateMethod = new ModelMapper();

        mapperForCreateMethod.addMappings(new PropertyMap<FeedbackEntity, FeedbackDto>() {
            @Override
            protected void configure() {

                skip().setLessonEntity(null);
            }
        });
        FeedbackEntity feedbackEntity = feedbackRepository.findById(feedbackDto.getId())
                .orElseThrow(() ->
                        new LearningPlatformException("Feedback Not Found with the following ID:" + feedbackDto.getId()));

        mapperForCreateMethod.map(feedbackDto, feedbackEntity);

        feedbackRepository.save(feedbackEntity);

        return mapperForCreateMethod.map(feedbackEntity, FeedbackDto.class);

    }

    @Override
    public void deleteFeedback(Long Id) throws LearningPlatformException {
        LOGGER.info("Deleting feedback with the following ID: " + Id);
        FeedbackEntity feedbackEntity = feedbackRepository.findById(Id).orElseThrow(()
                -> new LearningPlatformException("Feedback Not Found with the following ID:" + Id));
        feedbackRepository.delete(feedbackEntity);
    }


}
