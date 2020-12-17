package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder//thay the cho contructor
@Getter
@Setter
@ToString
public class AccountDetail {

    private int id;
    private String name;
    private String mobile;
    private int gender;
    private String address;

    public String getDisplayGender() {
        return (gender == 0) ? "Nữ" : (gender == 1) ? "Nam" : "Khác";
    }
}
