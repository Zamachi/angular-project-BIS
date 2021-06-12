package rs.ac.singidunum.appbackend.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class OrderModel {
    private String id;
    private Map<Integer, List<ProductModel>> productModel; // razmisliti;
    private UserModel userModel;
    private double totalPrice;
    private LocalDate dateCreated;
    private String status; // zavrsena, tekuca, otkazana
//    private int score; // samo ako je porudzbina zavrsena - kako ovo prikazati/resiti, da li se ovde zapravo cuva
}
