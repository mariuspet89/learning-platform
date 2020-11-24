package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.exceptionhandler.LearningPlatformException;
import eu.accesa.learningplatform.model.dto.FeedbackArchivedDto;
import eu.accesa.learningplatform.model.dto.FeedbackDto;
import eu.accesa.learningplatform.model.entity.FeedbackArchivedEntity;
import eu.accesa.learningplatform.model.entity.FeedbackEntity;
import eu.accesa.learningplatform.model.entity.LessonEntity;
import eu.accesa.learningplatform.repository.FeedbackArchivedRepository;
import eu.accesa.learningplatform.repository.FeedbackRepository;
import eu.accesa.learningplatform.repository.LessonRepository;
import eu.accesa.learningplatform.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    private final FeedbackRepository feedbackRepository;

    private final FeedbackArchivedRepository feedbackArchivedRepository;

    private final ModelMapper modelMapper;

    private final LessonRepository lessonRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               FeedbackArchivedRepository feedbackArchivedRepository,
                               ModelMapper modelMapper,
                               LessonRepository lessonRepository) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackArchivedRepository = feedbackArchivedRepository;
        this.modelMapper = modelMapper;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) {

        LOGGER.info("Creating Feedback " + feedbackDto.getId());
        FeedbackEntity feedbackEntity =
                feedbackRepository.save(modelMapper.map(feedbackDto, FeedbackEntity.class));
        return modelMapper.map(feedbackEntity, FeedbackDto.class);
    }

    @Override
    public List<FeedbackDto> getFeedbacksByLessonId(Long id) {
        LOGGER.info("Searching for the feedbacks related to the lesson with the ID " + id);

        LessonEntity lessonEntity = lessonRepository.findById(id).orElseThrow(()
                -> new LearningPlatformException("Lesson Not Found with the following ID:" + id));

        List<FeedbackEntity> feedbackEntities = feedbackRepository.findAllByLessonEntity_Id(id);

        return feedbackEntities.stream()
                .map(feedback -> modelMapper.map(feedback, FeedbackDto.class))
                .collect(toList());
    }

    @Override
    public FeedbackDto findFeedbackById(Long Id) {

        LOGGER.info("Searching for the Feedback with the following ID: " + Id);

        FeedbackEntity feedbackEntity = feedbackRepository.findById(Id)
                .orElseThrow(()
                        -> new LearningPlatformException("Feedback Not Found with the following ID:" + Id));
        return modelMapper.map(feedbackEntity, FeedbackDto.class);
    }

    @Override
    public FeedbackDto updateFeedback(FeedbackDto feedbackDto) {

        LOGGER.info("Updating Feedback " + feedbackDto.getId());

        FeedbackEntity feedbackEntity = feedbackRepository.findById(feedbackDto.getId())
                .orElseThrow(() ->
                        new LearningPlatformException("Feedback Not Found with the following ID:"
                                + feedbackDto.getId()));

        if (!feedbackEntity.getLessonEntity().getId().equals(feedbackDto.getLessonEntityId())) {

            throw new LearningPlatformException("You can't update the lesson ID");
        }

        modelMapper.map(feedbackDto, feedbackEntity);

        feedbackRepository.save(feedbackEntity);

        return modelMapper.map(feedbackEntity, FeedbackDto.class);
    }

    @Override
    public void deleteFeedback(Long Id) {

        LOGGER.info("Deleting feedback with the following ID: " + Id);

        FeedbackEntity feedbackEntity = feedbackRepository.findById(Id).orElseThrow(()
                -> new LearningPlatformException("Feedback Not Found with the following ID:" + Id));

        feedbackRepository.delete(feedbackEntity);
    }

    @Override
    public FeedbackArchivedDto archiveFeedback(Long id) {

        LOGGER.info("Archiving  Feedback with ID " + id);

        FeedbackEntity feedbackEntity = feedbackRepository.findById(id).orElseThrow(() ->
                new LearningPlatformException("Feedback Not Found with the following ID:" + id));

        if (feedbackEntity.getFeedbackArchivedEntity() != null) {
            throw new LearningPlatformException("Feedback Already Archived");
        }

        FeedbackArchivedEntity feedbackArchivedEntity = new FeedbackArchivedEntity();

        feedbackArchivedEntity.setFeedbackEntity(feedbackEntity);

        feedbackArchivedRepository.save(feedbackArchivedEntity);

        FeedbackArchivedEntity byId = feedbackArchivedRepository.findById(feedbackArchivedEntity.getId())
                .orElseThrow(() ->
                        new LearningPlatformException("No archived Feedback with ID" + feedbackArchivedEntity.getId()));

        feedbackEntity.setFeedbackArchivedEntity(byId);

        feedbackRepository.save(feedbackEntity);

        return modelMapper.map(feedbackArchivedEntity, FeedbackArchivedDto.class);
    }

    @Override
    public void undoArchive(Long id) {

        LOGGER.info("Undo Archiving  Feedback with ID " + id);

        FeedbackArchivedEntity feedbackArchivedEntity = feedbackArchivedRepository.findById(id)
                .orElseThrow(() -> new LearningPlatformException("Feedback Not Found with the following ID:" + id));
        FeedbackEntity feedbackEntity = feedbackRepository.getOne(feedbackArchivedEntity.getFeedbackEntity().getId());
        feedbackEntity.setFeedbackArchivedEntity(null);

        feedbackArchivedRepository.delete(feedbackArchivedEntity);
    }

    @Override
    public List<FeedbackArchivedDto> findAllArchivedFeedbacks() {
        LOGGER.info("Getting all archived feedbacks");

        List<FeedbackArchivedEntity> feedbackArchivedEntities = feedbackArchivedRepository.findAll();

        return feedbackArchivedEntities.stream()
                .map(fe -> modelMapper.map(fe, FeedbackArchivedDto.class))
                .collect(toList());
    }
}