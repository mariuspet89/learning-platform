package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDto> findFeedbackEntityByLesson_Id(Long id);

    FeedbackDto findFeedbackById(Long Id);

    FeedbackDto createFeedback(FeedbackDto feedbackDto);

    FeedbackDto updateFeedback(FeedbackDto feedbackDto);

    void deleteFeedback(Long Id);

}
