package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.entities.ReviewEntity;
import rs.ac.singidunum.appbackend.models.ReviewModel;
import rs.ac.singidunum.appbackend.repositories.iReviewRepository;

import java.util.List;

@Service
public class ReviewService implements iReviewService {
    @Autowired
    private iReviewRepository reviewRepository;

    @Autowired
    private AutoMapperService autoMapperService;

    @Override
    public ReviewEntity createReview(ReviewModel reviewModel) {
        return reviewRepository
                .insert(autoMapperService
                        .map(reviewModel,ReviewEntity.class));
    }

    @Override
    public ReviewEntity updateReview(ReviewModel reviewModel) {

        var review = autoMapperService
                .map(reviewModel, ReviewEntity.class);


        return reviewRepository.save(review);
    }

    @Override
    public List<ReviewEntity> getUserReviews(String username) {

        return reviewRepository.findAllByUser_Username(username);
    }

    @Override
    public List<ReviewEntity> findAllByProductId(String id) {
        return reviewRepository.findAllByProduct_Id(id);
    }

    @Override
    public List<ReviewEntity> findAllByUsernameAndProductId(String username, String id) {
        return reviewRepository.findByUser_UsernameAndProduct_Id(username,id);
    }

    @Override
    public List<ReviewEntity> findAll() {
        return reviewRepository.findAll();
    }


}
