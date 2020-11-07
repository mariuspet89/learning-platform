package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "LESSONS")
public class Lesson {
    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "DURATION")
    private double duration;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @ManyToMany
    @JoinColumn(
            name = "LESSONS_ASSOCIATION",
            joinColumns = @JoinColumn(name = "LESSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "LESSON_CONTENT_ID"))
    private List<Lesson_Content> lessonContents;*/

    public Lesson(Integer id, String name, String content, double duration) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.duration = duration;
    }

    public Lesson() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        Lesson lesson = (Lesson) o;
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
