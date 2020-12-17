package dao;

import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

public class AccountDao implements ICommonDao<Account> {

    public Account login(String email, String password) {
        String sql = "select * from account where email = ? AND password= ?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, email);
                ps.setObject(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs != null && rs.next()) {
                    Account account = Account.builder()
                            .id(rs.getInt(1))
                            .account_detail_id(rs.getInt(2))
                            .email(rs.getString(3))
                            .password(rs.getString(4))
                            .status(rs.getInt(5))
                            .build();
                    return account;

                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    @Override
    public List<Account> getAll() {
        String sql = "select *from account";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Account> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Account p = Account.builder()
                            .id(rs.getInt(1))
                            .account_detail_id(rs.getInt(2))
                            .email(rs.getString(3)).
                            password(rs.getString(4))
                            .status(rs.getInt(5))
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
    public Account getOne(int id) {
        String sql = "select * from account where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Account account = Account.builder()
                            .id(rs.getInt(1))
                            .account_detail_id(rs.getInt(2))
                            .email(rs.getString(3))
                            .password(rs.getString(4))
                            .status(rs.getInt(5))
                            .build();
                    return account;

                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;
    }

    @Override
    public boolean add(Account obj) {
        String sql = "INSERT INTO [dbo].[account]\n"
                + "           ([account_detail_id]\n"
                + "           ,[email]\n"
                + "           ,[password]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,1)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getAccount_detail_id());
                ps.setObject(2, obj.getEmail());
                ps.setObject(3, obj.getPassword());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;

    }

    @Override
    public boolean update(int id, Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override

    public boolean delete(int id) {
        String sql = "DELETE FROM account WHERE id=?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null) {
            if (ps != null) {
                ps.setObject(1, id);
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
