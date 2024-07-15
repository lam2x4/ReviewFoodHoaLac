package dao;

import Utility.Mapper;
import dal.DBContext;
import entity.Images;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOImages extends DBContext {

    public int addImages(Images img) throws SQLException {
        String sql = "INSERT INTO [dbo].[Images] "
                + "([blog_id], [link]) "
                + "VALUES (?,?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowAddImages(img, pre);

            return pre.executeUpdate();
        }
    }

    public int updateImages(Images img) throws SQLException {
        String sql = "UPDATE [dbo].[Images] "
                + "SET [blog_id] = ?, "
                + "[link] = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowUpdateImages(img, pre);

            return pre.executeUpdate();
        }
    }

    public int removeImages(int id) throws SQLException {
        String sql = "DELETE FROM Images WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);

            return pre.executeUpdate();
        }
    }

    public Vector<Images> getAll() throws SQLException {
        Vector<Images> vector = new Vector<>();
        String sql = "SELECT * FROM Images";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Images img = Mapper.mapRowImages(rs);

                vector.add(img);
            }
        }
        return vector;
    }

    public Images getImages(int id) throws SQLException {
        String sql = "SELECT * FROM Images WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    Images img = Mapper.mapRowImages(rs);

                    return img;
                } else {
                    throw new SQLException("No image found with ID: " + id);
                }
            }
        }
    }

    public Vector<Images> findImagesByBlog_id(int blog_id) throws SQLException {
        Vector<Images> vector = new Vector<>();
        String sql = "SELECT * FROM Images WHERE blog_id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, blog_id);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Images img = Mapper.mapRowImages(rs);

                vector.add(img);
            }
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOImages dao = new DAOImages();
        Images img = new Images(1, "New Link");

        try {

            System.out.println(dao.getAll());
        } catch (SQLException ex) {
            Logger.getLogger(DAOImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
