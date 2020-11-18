package eu.accesa.learningplatform.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PROGRAM")
public class ProgramEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "NAME")
    private String programName;
    @Column(name = "DESCRIPTION")
    private String description;
    @NotNull
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @NotNull
    @Column(name = "END_DATE")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "COMPETENCE_AREA_ID", referencedColumnName = "ID")
    private CompetenceAreaEntity competenceAreaEntity;

    @ManyToMany(mappedBy = "programEntities")
    private Set<UserEntity> userEntities;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CompetenceAreaEntity getCompetenceAreaEntity() {
        return competenceAreaEntity;
    }

    public void setCompetenceAreaEntity(CompetenceAreaEntity competenceAreaEntity) {
        this.competenceAreaEntity = competenceAreaEntity;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramEntity program = (ProgramEntity) o;
        return id.equals(program.id) &&
                programName.equals(program.programName) &&
                Objects.equals(description, program.description) &&
                startDate.equals(program.startDate) &&
                endDate.equals(program.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, programName, description, startDate, endDate);
    }
}
