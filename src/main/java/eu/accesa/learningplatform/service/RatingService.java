package eu.accesa.learningplatform.service;

import eu.accesa.learningplatform.model.dto.RatingDto;

import java.util.List;

public interface RatingService {

    RatingDto createRating(RatingDto ratingDto);

    List<RatingDto> getAllRatings();

    RatingDto updateRating(RatingDto ratingDto);

    void deleteRating(Long id);

    /*int createAverageRatingByCourse(Long id);
    int getAverageRatingByCourse(Long id);
    int updateAverageRatingByCourse(Long id);*/

}
