package eu.accesa.learningplatform.model.dto;

import eu.accesa.learningplatform.model.entity.LessonEntity;

import javax.validation.constraints.NotNull;

public class FeedbackDto {

    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;

    private LessonEntity lessonEntity;

    public LessonEntity getLessonEntity() {
        return lessonEntity;
    }

    public void setLessonEntity(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
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
