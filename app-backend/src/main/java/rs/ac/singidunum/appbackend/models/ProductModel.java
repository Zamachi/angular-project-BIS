package rs.ac.singidunum.appbackend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProductModel {
    @Id
    private String id;
    private String name;
    private String description;
    private int leftInStock;
    private double price;
    private String manufacturer;
    private double score; // proseka svih ocena za zadati proizvod; ukloniti ako zasmeta
    private String imagePath;
    private String modelPath;
    private AddressModel address; // mozda postaviti da isporuka uvek krene iz BG-a; ili da se svi proizvodi isporucuju iz razlicitih gradova
    // kategorije i vrste
    private String category;
}
