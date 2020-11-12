package eu.accesa.learningplatform.model.dto;

import eu.accesa.learningplatform.model.entity.CompetenceAreaEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Valid
public class ProgramDto {

    @NotNull
    private Long id;

    @NotNull
    private String programName;

    @NotNull
    private String description;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private CompetenceAreaEntity competenceAreaEntity; //competenceAreaDto?

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

    @Override
    public String toString() {
        return "ProgramDto{" +
                "id=" + id +
                ", programName='" + programName + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", competenceAreaEntity=" + competenceAreaEntity +
                '}';
    }
}
