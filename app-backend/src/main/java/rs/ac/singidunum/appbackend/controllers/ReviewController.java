package rs.ac.singidunum.appbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.appbackend.entities.ReviewEntity;
import rs.ac.singidunum.appbackend.models.ReviewModel;
import rs.ac.singidunum.appbackend.services.ReviewService;

import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    //NOTE: CREATE i READ, mozda i UPDATE (?)

    @PostMapping("createareview")
    @CrossOrigin(origins = "*")
    public ReviewEntity createReview(@RequestBody ReviewModel reviewModel){
        return reviewService.createReview(reviewModel);
    }

    @GetMapping("getuserreviews/{username}")
    @CrossOrigin(origins = "*")
    public List<ReviewEntity> getUserReviews(@PathVariable("username") String username){
        return reviewService.getUserReviews(username);
    }
    @GetMapping("findallbyproductid/{id}")
    @CrossOrigin(origins = "*")
    public List<ReviewEntity> findAllByProductId(@PathVariable("id") String id){
        return reviewService.findAllByProductId(id);
    }
    @GetMapping("getuserreviews/{username}/{id}")
    @CrossOrigin(origins = "*")
    public List<ReviewEntity> findAllByUsernameAndProductId(@PathVariable("username") String username, @PathVariable("id")String id){
        return reviewService.findAllByUsernameAndProductId(username,id);
    }

    @GetMapping("findallreviews")
    @CrossOrigin(origins = "*")
    public List<ReviewEntity> findAllReviews(){
        return reviewService.findAll();
    }

    @PutMapping("updatereview")
    @CrossOrigin(origins = "*")
    public ReviewEntity updateUserReview(@RequestBody ReviewModel reviewModel){
        return reviewService.updateReview(reviewModel);
    }

}
