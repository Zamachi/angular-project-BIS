package rs.ac.singidunum.appbackend.models;

import lombok.Data;

@Data
public class AddressModel {
    private String country;
    private String city;
    private String street;
    private int number;
    private int zipCode;
}
