package dao;

import Utility.Mapper;
import dal.DBContext;
import entity.CommentLikes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DAOCommentLikes extends DBContext {

    public int addCommentLikes(CommentLikes cl) throws SQLException {
        String sql = "INSERT INTO [dbo].[CommentLikes] "
                + "([blog_id], [user_id], [comment_id], [interaction_type]) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowAddCommentLikes(cl, pre);

            return pre.executeUpdate();
        }
    }

    public int updateCommentLikes(CommentLikes cl) throws SQLException {
        String sql = "UPDATE CommentLikes "
                + "SET interaction_type = ? "
                + "WHERE blog_id = ? AND user_id = ? AND comment_id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowUpdateCommentLikes(cl, pre);

            return pre.executeUpdate();
        }
    }

    public int deleteCommentLikes(int blog_id, int user_id, int comment_id) throws SQLException {
        String sql = "DELETE FROM CommentLikes WHERE blog_id = ? AND user_id = ? AND comment_id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, blog_id);
            pre.setInt(2, user_id);
            pre.setInt(3, comment_id);

            return pre.executeUpdate();
        }
    }

    public CommentLikes getCommentLikes(int blog_id, int user_id, int comment_id) throws SQLException {
        String sql = "SELECT * FROM CommentLikes WHERE blog_id = ? AND user_id = ? AND comment_id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, blog_id);
            pre.setInt(2, user_id);
            pre.setInt(3, comment_id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    CommentLikes cl = Mapper.mapRowCommentLikes(rs);

                    return cl;
                } else {
                    return null;
                }
            }
        }
    }
}
