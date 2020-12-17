package dao;

import entity.AccountDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import jdbc.SQLServerConnection;

public class AccountDetailDao implements ICommonDao<AccountDetail> {

    @Override
    public List<AccountDetail> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDetail getOne(int id) {
        String sql = "SELECT TOP (1000) [id]\n"
                + "      ,[name]\n"
                + "      ,[mobile]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "  FROM [banhDao].[dbo].[account_detail]\n"
                + "  where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    AccountDetail accountDetail = AccountDetail.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .mobile(rs.getString(3))
                            .gender(rs.getInt(4))
                            .address(rs.getString(5))
                            .build();
                    return accountDetail;

                }

            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return null;

    }

    public int addAccountReturnId(AccountDetail obj) {
        String query = "INSERT INTO [dbo].[account_detail]\n"
                + "           ([name]\n"
                + "           ,[mobile]\n"
                + "           ,[gender]\n"
                + "           ,[address])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getMobile());
                ps.setObject(3, obj.getGender());
                ps.setObject(4, obj.getAddress());
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
    public boolean add(AccountDetail obj) {
        String sql = "INSERT INTO [dbo].[account_detail]\n"
                + "           ([name]\n"
                + "           ,[mobile]\n"
                + "           ,[gender]\n"
                + "           ,[address])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getMobile());
                ps.setObject(3, obj.getGender());
                ps.setObject(4, obj.getAddress());

                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean update(int id, AccountDetail obj) {
        String sql = "UPDATE [dbo].[account_detail]\n"
                + "   SET [name] = ? \n"
                + "      ,[mobile] = ? \n"
                + "      ,[gender] = ? \n"
                + "      ,[address] = ? \n"
                + " WHERE id = ? ";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getName());
                ps.setObject(2, obj.getMobile());
                ps.setObject(3, obj.getGender());
                ps.setObject(4, obj.getAddress());
                ps.setObject(5, id);
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
