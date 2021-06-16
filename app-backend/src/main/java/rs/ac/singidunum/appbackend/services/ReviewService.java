package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.appbackend.repositories.iReviewRepository;

@Service
public class ReviewService implements iReviewService {
    @Autowired
    private iReviewRepository reviewRepository;
}
