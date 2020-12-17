package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CustomerInfo {

    private int id;
    private String name;
    private String mobile;
    private String email;
    private String address;
}
