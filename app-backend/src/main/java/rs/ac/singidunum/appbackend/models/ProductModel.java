package rs.ac.singidunum.appbackend.models;

import lombok.Data;

@Data
public class ProductModel {
    private String id;
    private String name;
    private String description;
    private int leftInStock;
    private double price;
    private String manufacturer;
    private double score; // prosek svih ocena za zadati proizvod; ukloniti ako zasmeta
    private String imagePath;
    private String modelPath;
    private AddressModel address; // mozda postaviti da isporuka uvek krene iz BG-a; ili da se svi proizvodi isporucuju iz razlicitih gradova
    private String category;
    private String subCategory;
}
