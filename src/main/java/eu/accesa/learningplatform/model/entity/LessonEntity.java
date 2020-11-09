package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "LESSON")
public class LessonEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "DURATION")
    private double duration;



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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonEntity lesson = (LessonEntity) o;
        return Double.compare(lesson.duration, duration) == 0 &&
                Objects.equals(id, lesson.id) &&
                Objects.equals(name, lesson.name) &&
                Objects.equals(content, lesson.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, duration);
    }
}
