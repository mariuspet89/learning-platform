package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.model.dto.FeedbackDto;
import eu.accesa.learningplatform.service.FeedbackService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public FeedbackDto save(@RequestBody FeedbackDto feedbackEntityDto) {
        return feedbackService.createFeedback(feedbackEntityDto);

    }

    @GetMapping("/{id}")
    public FeedbackDto findById(@PathVariable Long id) {
        return  feedbackService.findFeedbackById(id);
    }

    @PutMapping("/{id}")
    public FeedbackDto update(@PathVariable Long id, @RequestBody FeedbackDto feedbackEntityDto) {
        return feedbackService.updateFeedback(feedbackEntityDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }

    @GetMapping("/byLesson/{id}")
    public List<FeedbackDto> getFeedbackEntityByLesson_Id(@PathVariable Long id) {
        return feedbackService.findFeedbackEntityByLesson_Id(id);
    }


}
