package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import java.util.Objects;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "APPLICATION")
public class ApplicationEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "COURSE_IDEA")
    private String courseIdea;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseIdea() {
        return courseIdea;
    }

    public void setCourseIdea(String courseIdea) {
        this.courseIdea = courseIdea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationEntity)) return false;
        ApplicationEntity that = (ApplicationEntity) o;
        return getId().equals(that.getId()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getCourseIdea(), that.getCourseIdea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getCourseIdea());
    }
}
