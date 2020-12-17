package dao;

import entity.Product;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        ProductDao pd = new ProductDao();
        List<Product> list = pd.search("k");

//        l.forEach(System.out::println);//method reference
//            l.forEach(p -> System.out.println(p));//lamda
        if (list != null) {
            list.forEach(p -> System.out.println(p));//lamda

        } else {
            System.out.println("list null");
        }

//        System.out.println(pd.getOne(1));
//        Product p=Product.builder()
//                .brandId(11)
//                .name("tủ")
//                .price(500)
//                .size("M")
//                .color("vàng")
//                .quantity(5)
//                .description("nothing")
//                .status(1).build();
//        
//        System.out.println(pd.add(p)?"add success":"add fail");
//        System.out.println(pd.delete(5)?"update success":"add fail");
//z
    }
}
