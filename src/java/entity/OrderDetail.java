package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class OrderDetail {

    private int id;
    private int order_id;
    private int product_id;
    private String product_name;
    private double product_price;
    private int quantity;

}
