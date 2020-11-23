package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.model.dto.RatingDto;
import eu.accesa.learningplatform.service.RatingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ratings")
@OpenAPIDefinition
@Tag(name = "Ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<RatingDto>> getRatingsByLessonId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatingsByCourseId(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RatingDto save(@Valid @RequestBody RatingDto ratingDto) {
        return ratingService.updateRating(ratingDto);
    }

}
