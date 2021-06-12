package rs.ac.singidunum.appbackend.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewModel {
    private String id;
    private ProductModel productModel;
    private UserModel userModel;
    private int score;
    private String comment;
    private LocalDate dateCreated;
}
