/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Image;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author ADMIN
 */
public class ImgDao implements ICommonDao<Image> {

    @Override
    public List<Image> getAll() {
        String sql = "select * from images";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null) {
            if (ps != null) {
                ResultSet rs = ps.executeQuery();
                List<Image> list = new ArrayList<>();
                while (rs.next()) {
                    Image i = Image.builder().id(rs.getInt(1))
                            .proId(rs.getInt(2))
                            .imgName(rs.getString(3))
                            .picture_cover(rs.getBoolean(4)).build();
                    list.add(i);
                }
                return list;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Image> getAllByProID(int Id) {
        String sql = "select *from images where product_id =?";
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            ps.setObject(1, Id);
            if (ps != null) {
                ResultSet rs = ps.executeQuery();                  //execute query
                List<Image> list = new ArrayList<>();            //
                while (rs != null && rs.next()) {                  //rs.next trả về true false duyệt từng hàng 1
                    Image p = Image.builder()
                            .id(rs.getInt(1)).proId(rs.getInt(4))
                            .imgName(rs.getString(3)).picture_cover(rs.getBoolean(2))
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
    public Image getOne(int id) {
        String sql = "select * from images where id=?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null) {
            if (ps != null) {
                ps.setObject(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Image i = Image.builder().id(rs.getInt(1))
                            .proId(rs.getInt(2))
                            .imgName(rs.getString(3))
                            .picture_cover(rs.getBoolean(4)).build();
                    return i;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Image obj) {
        String sql = "INSERT INTO [dbo].[images]\n"
                + "           ([product_id]\n"
                + "           ,[imgName]\n"
                + "           ,[picture_cover])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getProId());
                ps.setObject(2, obj.getImgName());
                ps.setObject(3, obj.isPicture_cover());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean update(int id, Image obj) {
        String sql = "update images set id=?,product_id=?,imageName  =?,picture_cover=? where id=?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); //mở kết nối đến db
                PreparedStatement ps = (con != null) ? con.prepareStatement(sql) : null;//chuẩn bị câu query
                ) {
            if (ps != null) {
                ps.setObject(1, obj.getId());
                ps.setObject(2, obj.getProId());
                ps.setObject(3, obj.getImgName());
                ps.setObject(4, obj.isPicture_cover());
                check = ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);//in ra lỗi
        }

        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM images WHERE id=?";
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

    public List<Image> listImageCoverMapping(List<Image> listImage, List<Product> listProduct) {
        List<Image> listImageCoverMapping = new ArrayList<>();
        for (Product p : listProduct) {
            listImageCoverMapping.add(getImgByProId(p.getId(), listImage));

        }
        return listImageCoverMapping;
    }

    private Image getImgByProId(int productId, List<Image> list) {
        for (Image image : list) {
            if (image.getProId() == productId) {
                return image;
            }

        }
        return null;
    }
}
