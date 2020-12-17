package dao;

import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

public class CategoryDao implements ICommonDao<Category> {

    @Override
    public List<Category> getAll() {
        String sql = "select * from category";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Category> list = new ArrayList<>();
                while (rs.next()) {
                    Category i = Category.builder().id(rs.getInt(1))
                            .name(rs.getString(2))
                            .build();
                    list.add(i);
                }
                return list;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category getOne(int id) {
        String sql = "select * from category where id=?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Category i = Category.builder().id(rs.getInt(1))
                            .name(rs.getString(2))
                            .build();
                    return i;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Category obj) {
        String sql = "insert into category(id,name) values(?,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getId());
                ps.setObject(2, obj.getName());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean update(int id, Category obj) {
        String sql = "update category set id=?,name=?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getId());
                ps.setObject(2, obj.getName());

                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM category WHERE id=?";
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
