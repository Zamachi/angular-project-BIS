package rs.ac.singidunum.appbackend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import rs.ac.singidunum.appbackend.models.AddressModel;

@Data
@Document(collection = "products")
public class ProductEntity {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("description")
    private String description;
    @Field("leftInStock")
    private int leftInStock;
    @Field("price")
    private double price;
    @Field("manufacturer")
    private String manufacturer;
    @Field("score")
    private double score; // prosek svih ocena za zadati proizvod; ukloniti ako zasmeta
    @Field("imagePath")
    private String imagePath;
    @Field("modelPath")
    private String modelPath;
    @Field("address")
    private AddressModel address; // mozda postaviti da isporuka uvek krene iz BG-a; ili da se svi proizvodi isporucuju iz razlicitih gradova
    @Field("category")
    private String category;
    @Field("subCategory")
    private String subCategory;
}
