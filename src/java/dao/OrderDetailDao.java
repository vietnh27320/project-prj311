package dao;

import entity.Cart;
import entity.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

public class OrderDetailDao implements ICommonDao<OrderDetail> {

    @Override
    public List<OrderDetail> getAll() {
        String sql = "SELECT order_detail.*\n"
                + "FROM     order_detail";
        //luôn luôn cso xác suất xảy ra lỗi vs db
        //khách quan : mất internet
        //chủ quan: sai name password
        //:try():try with resource auto close thay cho finally vs close
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<OrderDetail> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    OrderDetail p = OrderDetail.builder()
                            .id(rs.getInt(1))
                            .order_id(rs.getInt(2))
                            .product_id(rs.getInt(3))
                            .product_name(rs.getString(4))
                            .product_price(rs.getDouble(5))
                            .quantity(rs.getInt(6))
                            .build();
                    list.add(p);
                }

                return list;
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    public boolean addListCart(List<Cart> list, int orderId) {
        String sql = "INSERT INTO [dbo].[order_detail]\n"
                + "           ([order_id]\n"
                + "           ,[product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[product_price]\n"
                + "           ,[quantity])\n"
                + "     values(?,?,?,?,?) ";
        int arr[] = {};
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {

            if (ps != null) {
                for (Cart cart : list) {
                    ps.setObject(1, orderId);
                    ps.setObject(2, cart.getProductId());
                    ps.setObject(3, cart.getProductName());
                    ps.setObject(4, cart.getProductPrice());
                    ps.setObject(5, cart.getQuantity());
                    ps.addBatch();
                }
                arr = ps.executeBatch();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr.length > 0;
    }

    @Override
    public OrderDetail getOne(int id) {
        String sql = "select *from  order_detail where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    OrderDetail p = OrderDetail.builder()
                            .id(rs.getInt(1))
                            .order_id(rs.getInt(2))
                            .product_id(rs.getInt(3))
                            .product_name(rs.getString(4))
                            .product_price(rs.getDouble(5))
                            .quantity(rs.getInt(6))
                            .build();
                    return p;

                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    @Override
    public boolean add(OrderDetail obj) {
        String sql = "INSERT INTO [dbo].[order_detail]\n"
                + "           ([order_id]\n"
                + "           ,[product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[product_price]\n"
                + "           ,[quantity])\n"
                + "     values(?,?,?,?,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getId());
                ps.setObject(2, obj.getProduct_id());
                ps.setObject(3, obj.getProduct_name());
                ps.setObject(4, obj.getQuantity());
                ps.setObject(5, obj.getProduct_price());
                ps.setObject(6, obj.getQuantity());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean update(int id, OrderDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
