package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.exceptionhandler.LearningPlatformException;
import eu.accesa.learningplatform.model.dto.FeedbackArchivedDto;
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
    public FeedbackDto save(@Valid @RequestBody FeedbackDto feedbackDto){
        return feedbackService.createFeedback(feedbackDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.findFeedbackById(id));
    }

    @PutMapping
    public ResponseEntity<FeedbackDto> update(@Valid @RequestBody FeedbackDto feedbackDto){
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.updateFeedback(feedbackDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.status(HttpStatus.OK).body("Feedback Deleted");
    }

    @GetMapping("/byLesson/{id}")
    public ResponseEntity<List<FeedbackDto>> getFeedbackByLessonId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getFeedbackByLessonId(id));
    }

    @PostMapping("/feedbacks/archived/{id}")
    public FeedbackArchivedDto archiveFeedback(@PathVariable Long id){

        return feedbackService.archiveFeedback(id);
    }

    @DeleteMapping("/feedbacks/archived/delete/{id}")
    public ResponseEntity<String> undoArchive(@Valid @PathVariable Long id)
            throws LearningPlatformException {
        feedbackService.undoArchive(id);
        return ResponseEntity.status(HttpStatus.OK).body(" Undo Archive Feedback");
    }

    @GetMapping("/feedbacks/archived")
    public ResponseEntity<List<FeedbackArchivedDto>> ArchivedFeedbacks() throws LearningPlatformException {

        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.findAllArchivedFeedbacks());
    }
}
