package eu.accesa.learningplatform.model.dto;

import eu.accesa.learningplatform.model.entity.CompetenceAreaEntity;

import java.time.LocalDate;

public class ProgramDto {

    private Long id;

    private String programName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

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
}
