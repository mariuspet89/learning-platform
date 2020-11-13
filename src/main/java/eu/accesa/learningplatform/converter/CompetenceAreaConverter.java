package eu.accesa.learningplatform.converter;

import eu.accesa.learningplatform.model.entity.CompetenceAreaEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CompetenceAreaConverter implements AttributeConverter<CompetenceAreaEnum, String> {

    @Override
    public String convertToDatabaseColumn(CompetenceAreaEnum competenceAreaEnum) {
        return competenceAreaEnum == null ? null : String.valueOf(competenceAreaEnum);
    }

    @Override
    public CompetenceAreaEnum convertToEntityAttribute(String competenceArea) {
        return competenceArea == null ? null : of(competenceArea);
    }

    public static CompetenceAreaEnum of(final String value) {
        for (final CompetenceAreaEnum competenceAreaEnum : CompetenceAreaEnum.values()) {
            if (competenceAreaEnum.name().equalsIgnoreCase(value)) {
                return competenceAreaEnum;
            }
        }
        throw new IllegalArgumentException("Unknown competence area type " + value);
    }
}