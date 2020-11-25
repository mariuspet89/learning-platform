package eu.accesa.learningplatform.configuration;

import eu.accesa.learningplatform.model.dto.AnswerDto;
import eu.accesa.learningplatform.model.dto.QuizDto;
import eu.accesa.learningplatform.model.dto.QuizItemDto;
import eu.accesa.learningplatform.model.entity.AnswerEntity;
import eu.accesa.learningplatform.model.entity.QuizEntity;
import eu.accesa.learningplatform.model.entity.QuizItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(QuizItemEntity.class, QuizItemDto.class)
                .addMapping(quizItemEntity -> quizItemEntity.getQuizItemType().getId(), QuizItemDto::setQuizItemTypeId);
        modelMapper.createTypeMap(QuizEntity.class, QuizDto.class)
                .addMapping(quizEntity -> quizEntity.getCourseEntity().getId(), QuizDto::setCourseId);
        modelMapper.createTypeMap(AnswerEntity.class, AnswerDto.class)
                .addMapping(answerEntity -> answerEntity.getQuizItem().getId(), AnswerDto::setQuizItemId);
        return modelMapper;
    }
}
