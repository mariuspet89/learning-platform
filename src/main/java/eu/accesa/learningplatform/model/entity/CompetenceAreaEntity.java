package eu.accesa.learningplatform.model.entity;

import eu.accesa.learningplatform.converter.CompetenceAreaConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "COMPETENCE_AREA")
public class CompetenceAreaEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private CompetenceAreaEnum name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompetenceAreaEnum getName() {
        return name;
    }

    public void setName(CompetenceAreaEnum name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetenceAreaEntity that = (CompetenceAreaEntity) o;
        return Objects.equals(id, that.id) &&
                name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
