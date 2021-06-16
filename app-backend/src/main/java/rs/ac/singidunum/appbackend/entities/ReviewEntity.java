package rs.ac.singidunum.appbackend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import rs.ac.singidunum.appbackend.models.ProductModel;
import rs.ac.singidunum.appbackend.models.UserModel;

import java.time.LocalDate;

@Data
@Document(collection = "reviews")
public class ReviewEntity {
    @Id
    private String id;
    @Field("productModel")
    private ProductModel productModel;
    @Field("userModel")
    private UserModel userModel;
    @Field("score")
    private int score;
    @Field("comment")
    private String comment;
    @Field("dateCreated")
    private LocalDate dateCreated;
}
