package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "APPLICATION_STATUS")
public class ApplicationStatusEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=AUTO)
    private Long id;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ApplicationStatusEnum status;

    @OneToMany(mappedBy = "applicationStatus")
    Set<ApplicationEntity> applicationEntitySet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatusEnum status) {
        this.status = status;
    }

    public Set<ApplicationEntity> getApplicationEntitySet() {
        return applicationEntitySet;
    }

    public void setApplicationEntitySet(Set<ApplicationEntity> applicationEntitySet) {
        this.applicationEntitySet = applicationEntitySet;
    }
}
