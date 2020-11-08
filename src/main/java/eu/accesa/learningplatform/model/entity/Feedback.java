package eu.accesa.learningplatform.model.entity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FEEDBACKS")
public class Feedback {

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) &&
                Objects.equals(title, feedback.title) &&
                Objects.equals(description, feedback.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }


}
