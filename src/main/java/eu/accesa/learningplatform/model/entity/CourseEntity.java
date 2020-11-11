package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "COURSE")
public class CourseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TOTAL_DURATION")
    private Double totalDuration;

    @ManyToOne
    @JoinColumn(name = "PROGRAM_ID")
    private ProgramEntity programEntity;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "courseEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LessonEntity> lessonEntities;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "QUIZ_ID")
    private QuizEntity quizEntity;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public ProgramEntity getProgramEntity() {
        return programEntity;
    }

    public void setProgramEntity(ProgramEntity programEntity) {
        this.programEntity = programEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<LessonEntity> getLessonEntities() {
        return lessonEntities;
    }

    public void setLessonEntities(List<LessonEntity> lessonEntities) {
        this.lessonEntities = lessonEntities;
    }

    public QuizEntity getQuizEntity() {
        return quizEntity;
    }

    public void setQuizEntity(QuizEntity quizEntity) {
        this.quizEntity = quizEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity courseEntity = (CourseEntity) o;
        return Objects.equals(id, courseEntity.id) &&
                Objects.equals(name, courseEntity.name) &&
                Objects.equals(description, courseEntity.description) &&
                Objects.equals(totalDuration, courseEntity.totalDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, totalDuration);
    }

}
