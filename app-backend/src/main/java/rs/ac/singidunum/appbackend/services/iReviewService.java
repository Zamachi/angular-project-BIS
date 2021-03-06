package rs.ac.singidunum.appbackend.services;

import rs.ac.singidunum.appbackend.entities.ReviewEntity;
import rs.ac.singidunum.appbackend.models.ReviewModel;

import java.util.List;

public interface iReviewService {
    ReviewEntity createReview(ReviewModel reviewModel);

    ReviewEntity updateReview(ReviewModel reviewModel);

    List<ReviewEntity> getUserReviews(String username);

    List<ReviewEntity> findAllByProductId(String id);

    List<ReviewEntity> findAllByUsernameAndProductId(String username, String id);

    List<ReviewEntity> findAll();

    List<ReviewEntity> findAllByUser_IdAndProduct_Id(String userId, String productId);
}