package dao;

import entity.Cart;
import entity.CustomerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

public class CustommerInfoDao implements ICommonDao<CustomerInfo> {

    public List<CustomerInfo> getAllOder(int id) {
        String sql = "SELECT customer_info.*\n"
                + "FROM customer_info";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<CustomerInfo> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    CustomerInfo p = CustomerInfo.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .mobile(rs.getString(3))
                            .email(rs.getString(4))
                            .address(rs.getString(5))
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

    @Override
    public List<CustomerInfo> getAll() {
        String sql = "SELECT customer_info.*\n"
                + "FROM     customer_info";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<CustomerInfo> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    CustomerInfo p = CustomerInfo.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .mobile(rs.getString(3))
                            .email(rs.getString(4))
                            .address(rs.getString(5))
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

    @Override
    public CustomerInfo getOne(int id) {
        String sql = "select * from  customer_info where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    CustomerInfo p = CustomerInfo.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .mobile(rs.getString(3))
                            .email(rs.getString(4))
                            .address(rs.getString(5))
                            .build();
                    return p;

                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    public int addCustomerInfoReturnId(CustomerInfo obj) {
        String query = "INSERT INTO customer_info(name, mobile, email, address) VALUES(?, ?, ?, ?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getMobile());
                ps.setObject(3, obj.getEmail());
                ps.setObject(4, obj.getAddress());
                int isCheck = ps.executeUpdate();
                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean add(CustomerInfo obj) {
        return false;

    }

    @Override
    public boolean update(int id, CustomerInfo obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
