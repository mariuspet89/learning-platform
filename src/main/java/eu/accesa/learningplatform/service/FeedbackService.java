package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.exceptionhandler.LearningPlatformException;
import eu.accesa.learningplatform.model.dto.FeedbackArchivedDto;
import eu.accesa.learningplatform.model.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDto> getFeedbackByLessonId(Long id);

    FeedbackDto findFeedbackById(Long Id);

    FeedbackDto createFeedback(FeedbackDto feedbackEntityDto);

    FeedbackDto updateFeedback(FeedbackDto feedbackEntityDto);

    void deleteFeedback(Long Id);

    FeedbackArchivedDto archiveFeedback(Long id);

    void undoArchive(Long id);

    List<FeedbackArchivedDto>  findAllArchivedFeedbacks();


}
