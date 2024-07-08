package dao;

import Utility.Mapper;
import dal.DBContext;
import entity.BlogLikes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOBlogLikes extends DBContext {

    public int addBlogLikes(BlogLikes bl) throws SQLException {
        String sql = "INSERT INTO [dbo].[BlogLikes] "
                + "([user_id], [blog_id], [interaction_type]) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowAddBlogLikes(bl, pre);

            return pre.executeUpdate();
        }
    }

    public int updateBlogLikes(BlogLikes bl) throws SQLException {
        String sql = "UPDATE BlogLikes "
                + "SET interaction_type = ? "
                + "WHERE user_id = ? AND blog_id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowUpdateBlogLikes(bl, pre);
            
            return pre.executeUpdate();
        }
    }

    public int deleteBlogLikes(int user_id, int blog_id) throws SQLException {
        String sql = "DELETE FROM BlogLikes WHERE user_id = ? AND blog_id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            pre.setInt(2, blog_id);

            return pre.executeUpdate();
        }
    }

    public BlogLikes getBlogLikes(int user_id, int blog_id) throws SQLException {
        String sql = "SELECT * FROM BlogLikes WHERE user_id = ? AND blog_id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            pre.setInt(2, blog_id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    BlogLikes bl = Mapper.mapRowBlogLikes(rs);

                    return bl;
                } else {
                    return null;
                }
            }
        }
    }
}
