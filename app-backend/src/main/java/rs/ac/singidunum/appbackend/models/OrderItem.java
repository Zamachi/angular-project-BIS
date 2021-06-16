package rs.ac.singidunum.appbackend.models;

import lombok.Data;

@Data
public class OrderItem {
    private ProductModel product;
    private int quantity;
}
