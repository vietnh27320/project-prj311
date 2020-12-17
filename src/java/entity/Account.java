package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder // replace constructor
@Getter
@Setter
@ToString
public class Account {

    private int id;
    private int account_detail_id;
    private String email;
    private String password;
    private int status;
    private int role_id;

    public String getDisplayStatus() {
        return status == 1 ? "<span style='color:green'> đang hoạt động</span>" : status == 2 ? "<span style='color:red'> Ngừng hoạt động</span>" : "tạm khóa";
    }

    public String getDisplayRole() {
        return role_id == 1 ? "Admin" : role_id == 2 ? "Staff" : "Member";
    }

}
