package dao;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.SQLServerConnection;

//các bướcc kết nối db
//1 mở kết nối
//2 excute qurey
//3 nhận kết quả
//4 xử lí về dạng list
//5 close connection 
//close vì 1 db chỉ mở song song khoảng vàu trăm connection
public class ProductDao implements ICommonDao<Product> {

    @Override
    public List<Product> getAll() {
        String sql = "select *from products";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Product> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Product p = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .name(rs.getString(3))
                            .price(rs.getDouble(4))
                            .description(rs.getString(5))
                            .status(rs.getInt(6))
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

    public List<Product> sort1() {
        String sql = "SELECT * FROM products\n"
                + "   ORDER BY price asc;";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Product> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Product p = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .name(rs.getString(3))
                            .price(rs.getDouble(4))
                            .description(rs.getString(5))
                            .status(rs.getInt(6))
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

    public List<Product> sortx() {
        String sql = "SELECT * FROM products\n"
                + "   ORDER BY price DESC;";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Product> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Product p = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .name(rs.getString(3))
                            .price(rs.getDouble(4))
                            .description(rs.getString(5))
                            .status(rs.getInt(6))
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

    public ArrayList<Product> paging(int pageindex, int pagesize) {
        ArrayList<Product> dummies = new ArrayList<>();
        String sql = "SELECT * FROM (SELECT *,ROW_NUMBER() OVER (ORDER BY ID ASC) as row_num FROM  products) tblDummy \n"
                + "WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";
        try {
            Connection connection = SQLServerConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pageindex);
            ps.setInt(2, pagesize);
            ps.setInt(3, pageindex);
            ps.setInt(4, pagesize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .categoryId(rs.getInt(2))
                        .name(rs.getString(3))
                        .price(rs.getDouble(4))
                        .description(rs.getString(5))
                        .status(rs.getInt(6))
                        .build();
                dummies.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dummies;
    }

    public int count() {
        String sql = "SELECT COUNT(*) as rownum FROM products";
        try {
            Connection connection = SQLServerConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("rownum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public List<Product> getAllByCateID(int categoryId) {

        String sql = "select *from products ";
        if ((categoryId != 0)) {
            sql += "where category_id =?";
        }

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;) {
            if (categoryId != 0) {
                ps.setObject(1, categoryId);
            }
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Product> list = new ArrayList<>();
                while (rs != null && rs.next()) {
                    Product p = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .name(rs.getString(3))
                            .price(rs.getDouble(4))
                            .description(rs.getString(5))
                            .status(rs.getInt(6))
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
    public Product getOne(int id) {
        String sql = "select *from products where id=?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();               //execute query
                if (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Product p = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .name(rs.getString(3))
                            .price(rs.getDouble(4))
                            .description(rs.getString(5))
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

    @Override
    public boolean add(Product obj) {
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([category_id]\n"
                + "           ,[name]\n"
                + "           ,[price]\n"
                + "           ,[description]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getCategoryId());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.getPrice());

                ps.setObject(4, obj.getDescription());
                ps.setObject(5, obj.getStatus());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    public int addProductReturnId(Product obj) {
        String query = "INSERT INTO [dbo].[products]\n"
                + "           ([category_id]\n"
                + "           ,[name]\n"
                + "           ,[price]\n"
                + "           ,[description]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            if (ps != null) {
                ps.setObject(1, obj.getCategoryId());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.getPrice());

                ps.setObject(4, obj.getDescription());
                ps.setObject(5, obj.getStatus());
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
    public boolean update(int id, Product obj) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [brand_id] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[description] =?\n"
                + "      ,[status] = ?\n"
                + " WHERE id=?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getCategoryId());
                ps.setObject(2, obj.getName());
                ps.setObject(3, obj.getPrice());

                ps.setObject(4, obj.getDescription());
                ps.setObject(5, obj.getStatus());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM [dbo].[products]\n"
                + "      WHERE id=?";
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

    public List<Product> search(String text) {
        String sql = "select * from products where name like  ?";

        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            ps.setObject(1, "%" + text + "%");
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Product> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Product p = Product.builder()
                            .id(rs.getInt(1))
                            .categoryId(rs.getInt(2))
                            .name(rs.getString(3))
                            .price(rs.getDouble(4))
                            .description(rs.getString(5))
                            .status(rs.getInt(6))
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

}
