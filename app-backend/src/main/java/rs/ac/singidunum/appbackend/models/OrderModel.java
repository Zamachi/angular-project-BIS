package rs.ac.singidunum.appbackend.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderModel {
    private String id;
    private List<OrderItem> items;
    private UserModel user;
    private double totalPrice;
    private LocalDate dateCreated;
    private String status; // complete, ongoign, cancelled
    private String paymentOption;
    private int minEta;
//    private int score; // samo ako je porudzbina zavrsena - kako ovo prikazati/resiti, da li se ovde zapravo cuva
}
