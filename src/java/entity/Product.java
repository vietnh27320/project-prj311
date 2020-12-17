package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//gd1 anh xa tu 1 bang sang doi tuong
@Builder
@Getter
@Setter
@ToString
public class Product {

    private int id;
    private int categoryId;
    private String name;
    private double price;
    private String description;
    private int status;

    public String getDisplayStatus() {
        return status == 1 ? "còn hàng" : status == 2 ? " giảm giá" : "ngừng kinh doanh";
    }

}
