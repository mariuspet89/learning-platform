package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.exceptionhandler.EmptyFieldsException;
import eu.accesa.learningplatform.exceptionhandler.LearningPlatformException;
import eu.accesa.learningplatform.model.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDto> findFeedbackEntityByLesson_Id(Long id) throws LearningPlatformException;

    FeedbackDto findFeedbackById(Long Id) throws LearningPlatformException;

    FeedbackDto createFeedback(FeedbackDto feedbackEntityDto) throws LearningPlatformException, EmptyFieldsException;

    FeedbackDto updateFeedback(FeedbackDto feedbackEntityDto) throws LearningPlatformException;

    void deleteFeedback(Long Id) throws LearningPlatformException;

}
