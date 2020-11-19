package eu.accesa.learningplatform.model.dto;

import javax.validation.constraints.NotNull;

public class LessonDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private double duration;
    @NotNull
    private Long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "LessonDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", courseEntityId=" + courseId +
                '}';
    }
}
