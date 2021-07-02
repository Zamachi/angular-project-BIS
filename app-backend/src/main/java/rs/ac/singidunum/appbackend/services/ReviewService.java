package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.entities.ReviewEntity;
import rs.ac.singidunum.appbackend.models.ProductModel;
import rs.ac.singidunum.appbackend.models.ReviewModel;
import rs.ac.singidunum.appbackend.models.UserModel;
import rs.ac.singidunum.appbackend.repositories.iProductRepository;
import rs.ac.singidunum.appbackend.repositories.iReviewRepository;
import rs.ac.singidunum.appbackend.repositories.iUserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService implements iReviewService {
    @Autowired
    private iReviewRepository reviewRepository;

    @Autowired
    private AutoMapperService autoMapperService;

    @Autowired
    private iProductRepository productRepository;

    @Autowired
    private iUserRepository userRepository;

    @Override
    public ReviewEntity createReview(ReviewModel reviewModel) {
        //NOTE: mzoda bi mogla da se izvrsi provera da li je Order status =="complete" pre no sto se izvrs insert
        reviewModel.setDateCreated(LocalDate.now());
        reviewModel.setUser( autoMapperService.map(userRepository.findById( reviewModel.getUser().getId() ).get(), UserModel.class) );

        //NOTE: modify the original product

        var product = productRepository.findById(reviewModel.getProduct().getId()).get();

        int number_of_reviews = findAllByProductId( product.getId() ).size();
        product.setScore( product.getScore() + ( (reviewModel.getScore() - product.getScore() ) / (number_of_reviews+1) ) );

        reviewModel.setProduct( autoMapperService.map( product, ProductModel.class) );

        productRepository.save(product);
        //NOTE: modify the original product

        return reviewRepository
                .insert(autoMapperService
                        .map(reviewModel,ReviewEntity.class)
                );
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
