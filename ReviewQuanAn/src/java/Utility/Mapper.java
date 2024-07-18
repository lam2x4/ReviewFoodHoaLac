package Utility;

import entity.*;
import dao.DAOComment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Mapper {

    //User---------------------------------------------------------------------
    public static User mapRowUser(ResultSet rs) throws SQLException {
        User u = new User();

        u.setId(rs.getInt(1));
        u.setUsername(rs.getString(2));
        u.setPassword(rs.getString(3));
        u.setEmail(rs.getString(4));
        u.setPhone(rs.getString(5));
        u.setAvatar(rs.getString(6));
        u.setGender(rs.getInt(7));
        u.setDescription(rs.getString(8));
        u.setCreate_date(rs.getString(9));
        u.setVerify_status(rs.getInt(10));
        u.setRole_id(rs.getInt(11));

        return u;
    }

    public static void setRowUpdateUser(User user, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPhone());
        pstmt.setString(5, user.getAvatar());
        pstmt.setInt(6, user.getGender());
        pstmt.setString(7, user.getDescription());
        pstmt.setString(8, user.getCreate_date());
        pstmt.setInt(9, user.getVerify_status());
        pstmt.setInt(10, user.getRole_id());
        pstmt.setInt(11, user.getId());
    }

    public static void setRowAddUser(User user, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPhone());
        pstmt.setString(5, user.getAvatar());
        pstmt.setInt(6, user.getGender());
        pstmt.setString(7, user.getDescription());
        pstmt.setString(8, user.getCreate_date());
        pstmt.setInt(9, user.getVerify_status());
        pstmt.setInt(10, user.getRole_id());
    }
    //User---------------------------------------------------------------------

    //Blog---------------------------------------------------------------------
    public static void setRowAddBlog(Blog b, PreparedStatement pre) throws SQLException {
        pre.setInt(1, b.getUser_id());
        pre.setString(2, b.getTitle());
        pre.setString(3, b.getContent());
        pre.setString(4, b.getCreate_date());
        pre.setInt(5, b.getLikes());
        pre.setInt(6, b.getIs_approved());
        pre.setInt(7, b.getIs_banned());
        pre.setInt(8, b.getAuthor_id());
    }

    public static void setRowEditBlog(Blog b, PreparedStatement pre) throws SQLException {
        pre.setInt(1, b.getUser_id());
        pre.setString(2, b.getTitle());
        pre.setString(3, b.getContent());
        pre.setString(4, b.getCreate_date());
        pre.setInt(5, b.getLikes());
        pre.setInt(6, b.getIs_approved());
        pre.setInt(7, b.getIs_banned());
        pre.setInt(8, b.getAuthor_id());
        pre.setInt(9, b.getId());
    }

    public static Blog mapRowBlog(ResultSet rs) throws SQLException {
        Blog b = new Blog();

        b.setId(rs.getInt(1));
        b.setUser_id(rs.getInt(2));
        b.setTitle(rs.getString(3));
        b.setContent(rs.getString(4));
        b.setCreate_date(rs.getString(5));
        b.setLikes(rs.getInt(6));
        b.setIs_approved(rs.getInt(7));
        b.setIs_banned(rs.getInt(8));
        b.setAuthor_id(rs.getInt(9));

        return b;
    }
    //Blog---------------------------------------------------------------------

    //BlogLikes----------------------------------------------------------------
    public static void setRowAddBlogLikes(BlogLikes bl, PreparedStatement pre) throws SQLException {
        pre.setInt(1, bl.getUser_id());
        pre.setInt(2, bl.getBlog_id());
        pre.setString(3, bl.getInteraction_type());
    }

    public static void setRowUpdateBlogLikes(BlogLikes bl, PreparedStatement pre) throws SQLException {
        pre.setString(1, bl.getInteraction_type());
        pre.setInt(2, bl.getUser_id());
        pre.setInt(3, bl.getBlog_id());
    }

    public static BlogLikes mapRowBlogLikes(ResultSet rs) throws SQLException {
        BlogLikes bl = new BlogLikes();

        bl.setUser_id(rs.getInt(1));
        bl.setBlog_id(rs.getInt(2));
        bl.setInteraction_type(rs.getString(3));

        return bl;
    }
    //BlogLikes----------------------------------------------------------------

    //Comment------------------------------------------------------------------
    public static void setRowAddComment(Comment comm, PreparedStatement pre) throws SQLException {
        pre.setInt(1, comm.getUser_id());
        pre.setInt(2, comm.getBlog_id());
        pre.setString(3, comm.getContent());
        pre.setString(4, comm.getCreate_date());
        pre.setInt(5, comm.getLikes());
        pre.setInt(6, comm.getIs_banned());
    }

    public static void setRowEditComment(Comment comm, PreparedStatement pre) throws SQLException {
        pre.setString(1, comm.getContent());
        pre.setInt(2, comm.getId());
    }

    public static Comment mapRowComment(ResultSet rs) throws SQLException {
        DAOComment dao = new DAOComment();
        Comment comm = new Comment();

        comm.setId(rs.getInt(1));
        comm.setUser_id(rs.getInt(2));
        comm.setUsername(dao.findUsername(rs.getInt(2)));
        comm.setBlog_id(rs.getInt(3));
        comm.setContent(rs.getString(4));
        comm.setCreate_date(rs.getString(5));
        comm.setLikes(rs.getInt(6));
        comm.setIs_banned(7);

        return comm;
    }
    //Comment------------------------------------------------------------------

    //CommentLikes-------------------------------------------------------------
    public static void setRowAddCommentLikes(CommentLikes cl, PreparedStatement pre) throws SQLException {
        pre.setInt(1, cl.getBlog_id());
        pre.setInt(2, cl.getUser_id());
        pre.setInt(3, cl.getComment_id());
        pre.setString(4, cl.getInteraction_type());
    }

    public static void setRowUpdateCommentLikes(CommentLikes cl, PreparedStatement pre) throws SQLException {
        pre.setString(1, cl.getInteraction_type());
        pre.setInt(2, cl.getBlog_id());
        pre.setInt(3, cl.getUser_id());
        pre.setInt(4, cl.getComment_id());
    }

    public static CommentLikes mapRowCommentLikes(ResultSet rs) throws SQLException {
        CommentLikes cl = new CommentLikes();

        cl.setBlog_id(rs.getInt(1));
        cl.setUser_id(rs.getInt(2));
        cl.setComment_id(rs.getInt(3));
        cl.setInteraction_type(rs.getString(4));

        return cl;
    }
    //CommentLikes-------------------------------------------------------------

    //Images-------------------------------------------------------------------
    public static void setRowAddImages(Images img, PreparedStatement pre) throws SQLException {
        pre.setInt(1, img.getBlog_id());
        pre.setString(2, img.getLink());
    }

    public static void setRowUpdateImages(Images img, PreparedStatement pre) throws SQLException {
        pre.setInt(1, img.getBlog_id());
        pre.setString(2, img.getLink());
        pre.setInt(3, img.getId());
    }

    public static Images mapRowImages(ResultSet rs) throws SQLException {
        Images img = new Images();

        img.setId(rs.getInt(1));
        img.setBlog_id(rs.getInt(2));
        img.setLink(rs.getString(3));

        return img;
    }
    //Images-------------------------------------------------------------------

    //Role---------------------------------------------------------------------
    public static void setRowAddRole(Role r, PreparedStatement pre) throws SQLException {
        pre.setInt(1, r.getRole_id());
        pre.setString(2, r.getRole_name());
    }

    public static void setRowEditRole(Role r, PreparedStatement pre) throws SQLException {
        pre.setString(1, r.getRole_name());
        pre.setInt(2, r.getRole_id());
    }

    public static Role mapRowRole(ResultSet rs) throws SQLException {
        Role r = new Role();

        r.setRole_id(rs.getInt(1));
        r.setRole_name(rs.getString(2));

        return r;
    }
    //Role---------------------------------------------------------------------
}
