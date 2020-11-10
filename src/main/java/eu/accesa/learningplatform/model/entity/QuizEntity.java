package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "QUIZ")
public class QuizEntity {
    @Id
    @Column(name="QUIZ_ID")
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(name="TITLE")
    private String title;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizEntity that = (QuizEntity) o;
        return id.equals(that.id) &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
