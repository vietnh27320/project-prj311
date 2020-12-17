
package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Cart {
    private int productId;
    private String productImage;
    private String productName;
    private double productPrice;
    private  int quantity;
    private double totalPrice;
    
}
