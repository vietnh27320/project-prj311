package dao;

import entity.CustomerInfo;
import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

public class OrderDao implements ICommonDao<Order> {

    @Override
    public List<Order> getAll() {
        String sql = "SELECT [order].*\n"
                + "FROM     [order]";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Order> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Order p = Order.builder()
                            .id(rs.getInt(1))
                            .customer_info_id(rs.getInt(2))
                            .total_price(rs.getDouble(3))
                            .note(rs.getString(4))
                            .date(rs.getString(5))
                            .status(rs.getInt(6))
                            .accountId(rs.getInt(7))
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
    public Order getOne(int id) {
        String sql = "select *from  [order] where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Order p = Order.builder()
                            .id(rs.getInt(1))
                            .customer_info_id(rs.getInt(2))
                            .total_price(rs.getDouble(3))
                            .note(rs.getString(4))
                            .date(rs.getString(5))
                            .status(rs.getInt(6))
                            .build();
                    return p;

                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    public Order getOnebyAccountId(int aid) {
        String sql = "SELECT     [order].*\n"
                + "FROM        [order]\n"
                + "where account_id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, aid);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Order p = Order.builder()
                            .id(rs.getInt(1))
                            .customer_info_id(rs.getInt(2))
                            .total_price(rs.getDouble(3))
                            .note(rs.getString(4))
                            .date(rs.getString(5))
                            .status(rs.getInt(6))
                            .build();
                    return p;

                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    public int addOrderReturnId(Order obj) {
        String query = "INSERT INTO [order](customer_info_id, total_price, note, status, account_id) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCustomer_info_id());
                ps.setObject(2, obj.getTotal_price());
                ps.setObject(3, obj.getNote());
                ps.setObject(4, obj.getStatus());
                ps.setObject(5, obj.getAccountId());
                int isCheck = ps.executeUpdate();
                if (isCheck > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    rs.next();
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    @Override
    public boolean add(Order obj) {
        String sql = "INSERT INTO [dbo].[order]\n"
                + "           ([customer_info_id]\n"
                + "           ,[total_price]\n"
                + "           ,[note]\n"
                + "           ,[create_date]\n"
                + "           ,[status]) values(?,?,?,?,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getCustomer_info_id());
                ps.setObject(2, obj.getTotal_price());
                ps.setObject(3, obj.getNote());
                ps.setObject(4, obj.getDate());
                ps.setObject(5, obj.getStatus());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean update(int id, Order obj) {

        String sql = "UPDATE [dbo].[order]\n"
                + "   SET \n"
                + "      [status] = 1\n"
                + "    \n"
                + " WHERE id=?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {

                ps.setObject(1, obj.getStatus());
                ps.setObject(2, id);
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
