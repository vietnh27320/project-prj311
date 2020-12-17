package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Image {

    private int id;
    private int proId;
    private String imgName;
    private boolean picture_cover;
}
