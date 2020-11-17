package eu.accesa.learningplatform.controller;

import eu.accesa.learningplatform.model.dto.LessonDto;
import eu.accesa.learningplatform.service.LessonService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@OpenAPIDefinition
@Tag(name = "Lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LessonDto> save(@RequestBody LessonDto lessonDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.createLesson(lessonDto));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LessonDto> getLessonById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(lessonService.getLessonById(id));
    }

    @GetMapping("course/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LessonDto>> getLessonsByCourseId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(lessonService.getLessonsByCourse(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LessonDto> updateLesson(@RequestBody LessonDto lessonDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.updateLesson(lessonDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
}
