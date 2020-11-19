package eu.accesa.learningplatform.model.dto;

import javax.validation.constraints.NotBlank;

public class FeedbackDto {

    private Long id;

    @NotBlank(message = "Empty Title Field")
    private String title;

    @NotBlank(message = "Empty Description Field")
    private String description;


    private Long lessonEntityId;

    public Long getLessonEntityId() {
        return lessonEntityId;
    }

    public void setLessonEntityId(Long lessonEntityId) {
        this.lessonEntityId = lessonEntityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
