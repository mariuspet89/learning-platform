package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.exceptionhandler.EmptyFieldsException;
import eu.accesa.learningplatform.exceptionhandler.LearningPlatformException;
import eu.accesa.learningplatform.model.dto.FeedbackDto;
import eu.accesa.learningplatform.service.FeedbackService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@OpenAPIDefinition
@Tag(name = "Feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackDto save(@RequestBody FeedbackDto feedbackDto) throws LearningPlatformException, EmptyFieldsException {
        return feedbackService.createFeedback(feedbackDto);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FeedbackDto> findById(@Valid @PathVariable Long id)
            throws LearningPlatformException {
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.findFeedbackById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FeedbackDto> update(@Valid @PathVariable Long id,
                                              @RequestBody FeedbackDto feedbackDto)
            throws LearningPlatformException {

        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.updateFeedback(feedbackDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteById(@Valid @PathVariable Long id)
            throws LearningPlatformException {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.status(HttpStatus.OK).body("Feedback Deleted");
    }

    @GetMapping("/byLesson/{id}")
    public ResponseEntity<List<FeedbackDto>> getFeedbackEntityByLesson_Id(@Valid @PathVariable Long id)
            throws LearningPlatformException {
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.findFeedbackEntityByLesson_Id(id));
    }

}
