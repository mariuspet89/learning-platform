package eu.accesa.learningplatform.model.converters;
import eu.accesa.learningplatform.model.entity.QuizItemTypeEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class QuizItemTypeConverter implements AttributeConverter<QuizItemTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(QuizItemTypeEnum quizItemTypeEnum) {
        if (quizItemTypeEnum == null) {
            return null;
        }
        return quizItemTypeEnum.getCode();
    }

    @Override
    public QuizItemTypeEnum convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(QuizItemTypeEnum.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
