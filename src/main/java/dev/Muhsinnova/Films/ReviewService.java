package dev.Muhsinnova.Films;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService
{
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(String reviewBody, String imdbId)
    {
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Film.class).matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        //checks if the received imdb id from the user matches the one in the database
        //then the apply creates and updates the reviews

        return review;

    }

}
