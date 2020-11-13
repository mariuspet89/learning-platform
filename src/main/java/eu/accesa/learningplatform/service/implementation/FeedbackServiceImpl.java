package eu.accesa.learningplatform.service.implementation;

import eu.accesa.learningplatform.model.dto.FeedbackDto;
import eu.accesa.learningplatform.model.entity.FeedbackEntity;
import eu.accesa.learningplatform.repository.FeedbackRepository;
import eu.accesa.learningplatform.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ModelMapper modelMapper) {
        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FeedbackDto findFeedbackById(Long Id) {

        FeedbackEntity feedbackEntity = feedbackRepository.findById(Id)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found"));
        return modelMapper.map(feedbackEntity, FeedbackDto.class);

    }

    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackEntityDto) {

        FeedbackEntity feedbackEntity = feedbackRepository
                .save(modelMapper.map(feedbackEntityDto, FeedbackEntity.class));
        return modelMapper.map(feedbackEntity, FeedbackDto.class);

    }

    @Override
    public FeedbackDto updateFeedback(FeedbackDto feedbackEntityDto) {

        ModelMapper mapperForCreateMethod = new ModelMapper();
        mapperForCreateMethod.addMappings(new PropertyMap<FeedbackEntity, FeedbackDto>() {
            @Override
            protected void configure() {
                skip().setLessonEntity(null);
            }
        });
        FeedbackEntity feedbackEntity = feedbackRepository.findById(feedbackEntityDto.getId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found"));

        mapperForCreateMethod.map(feedbackEntityDto, feedbackEntity);

        return mapperForCreateMethod.map(feedbackEntity, FeedbackDto.class);

    }

    @Override
    public void deleteFeedback(Long Id) {

        FeedbackEntity feedbackEntity = feedbackRepository.findById(Id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found"));
        feedbackRepository.delete(feedbackEntity);

    }

    @Override
    public List<FeedbackDto> findFeedbackEntityByLesson_Id(Long id) {

        List<FeedbackEntity> feedbackEntities = feedbackRepository.findAllByLessonEntity_Id(id);
        return feedbackEntities.stream()
                .map(feedbackEntity -> modelMapper.map(feedbackEntity, FeedbackDto.class))
                .collect(toList());
    }

}
