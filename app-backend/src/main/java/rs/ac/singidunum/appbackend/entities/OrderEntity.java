package rs.ac.singidunum.appbackend.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import rs.ac.singidunum.appbackend.models.OrderItem;
import rs.ac.singidunum.appbackend.models.UserModel;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "orders")
public class OrderEntity {
    @Id
    private String id;
    @Field("items")
    private List<OrderItem> items;
    @Field("user")
    private UserModel user;
    @Field("totalPrice")
    private double totalPrice;
    @Field("dateCreated")
    private LocalDate dateCreated;
    @Field("status")
    private String status; // zavrsena, tekuca, otkazana
    @Field("paymentOption")
    private String paymentOption;
}
