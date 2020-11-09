package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "job_titles")
public class JobTitle {

    @Id
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "job_title")
    private Job job;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobTitle jobTitle = (JobTitle) o;
        return Objects.equals(id, jobTitle.id) &&
                job == jobTitle.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, job);
    }
}
