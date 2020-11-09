package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitleEntity {

    @Id
    @Column(name = "ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "JOB_TITLE")
    private JobTitleEnum jobTitleEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobTitleEnum getJob() {
        return jobTitleEnum;
    }

    public void setJob(JobTitleEnum jobTitleEnum) {
        this.jobTitleEnum = jobTitleEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobTitleEntity jobTitleEntity = (JobTitleEntity) o;
        return Objects.equals(id, jobTitleEntity.id) &&
                jobTitleEnum == jobTitleEntity.jobTitleEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitleEnum);
    }
}
