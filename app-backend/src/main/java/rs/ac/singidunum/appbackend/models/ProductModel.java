package rs.ac.singidunum.appbackend.models;

import lombok.Data;

@Data
public class ProductModel {
    private String id;
    private String slug;
    private String name;
    private String description;
    private int leftInStock;
    private double price;
    private String manufacturer;
    private double score; // prosek svih ocena za zadati proizvod; ukloniti ako zasmeta
    private String imagePath;
    private String modelPath;
    private AddressModel address; //NOTE: neophodno, za generisanje distance koristiti random vrednosti
    private String category;
    private String subCategory;
}
