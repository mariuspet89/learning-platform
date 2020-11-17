package eu.accesa.learningplatform.model.dto;

import com.sun.istack.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class LessonDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private double duration;
    @Nullable
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDto lessonDto = (LessonDto) o;
        return Double.compare(lessonDto.duration, duration) == 0 &&
                Objects.equals(id, lessonDto.id) &&
                Objects.equals(name, lessonDto.name) &&
                Objects.equals(courseId, lessonDto.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, courseId);
    }
}
