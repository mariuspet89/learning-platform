package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.RatingDto;

import java.util.List;

public interface RatingService {

    RatingDto createRating(RatingDto ratingDto);

    List<RatingDto> getAllRatingsByCourseId(Long id);

    RatingDto updateRating(RatingDto ratingDto);

    void deleteRating(Long id);

    Double getAverageRatingByCourseId(Long id);
}
