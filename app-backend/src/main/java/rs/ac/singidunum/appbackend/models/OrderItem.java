package rs.ac.singidunum.appbackend.models;

import lombok.Data;

@Data
public class OrderItem {
    private ProductModel productModel;
    private int quantity;
}
