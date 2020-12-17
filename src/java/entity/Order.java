package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Order {

    private int id;
    private int customer_info_id;
    private double total_price;
    private String note;
    private String date;
    private int status;
    private int accountId;

    public String getDisplayStatus() {
        return status == 1 ? "<span style='color:green'> đã xử lí</span>" : status == 2 ? "<span style='color:yellow'> đang xử lí</span>" : "<span style='color:red'> đã hủy</span>";
    }
}
