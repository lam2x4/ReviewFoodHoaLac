package dao;

import Utility.BlogFilterDate;
import Utility.BlogFilterPop;
import Utility.Mapper;
import dal.DBContext;
import entity.Blog;
import java.awt.BorderLayout;
import java.io.Console;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOBlog extends DBContext {

    public int addBlog(Blog b) throws SQLException {
        String sql = "INSERT INTO [dbo].[Blog] "
                + "([user_id],[title],[content],[create_date],[likes],[is_approved],[is_banned], [author_id]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowAddBlog(b, pre);

            return pre.executeUpdate();
        }
    }

    public int updateBlogUser_id(int user_id) throws SQLException {
        String sql = "UPDATE [dbo].[Blog] "
                + "SET [user_id] = null "
                + "WHERE [user_id] = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);

            return pre.executeUpdate();
        }
    }

    public int updateLikes(int blog_id, int likes) throws SQLException {
        String sql = "UPDATE Blog "
                + "SET likes = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, likes);
            pre.setInt(2, blog_id);

            return pre.executeUpdate();
        }
    }

    public int editBlog(Blog b) throws SQLException {
        String sql = "UPDATE [dbo].[Blog] "
                + "SET [user_id] = ?, "
                + "[title] = ?, "
                + "[content] = ?, "
                + "[create_date] = ?, "
                + "[likes] = ?, "
                + "[is_approved] = ?, "
                + "[is_banned] = ?, "
                + "[author_id] = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowEditBlog(b, pre);

            return pre.executeUpdate();
        }
    }

    public int deleteBlog(int id) throws SQLException {
        String sql = "DELETE FROM Blog WHERE id = ? "
                + "AND id NOT IN (SELECT blog_id FROM Comment WHERE blog_id = ?) "
                + "AND id NOT IN (SELECT blog_id FROM Images WHERE blog_id = ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.setInt(2, id);
            pre.setInt(3, id);

            return pre.executeUpdate();
        }
    }

    public int deleteBlogIgnoreConstraint(int id) throws SQLException {
        String sql = "delete from images where blog_id=? "
                + "delete from comment where blog_id=? "
                + "delete from blog where id=?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.setInt(2, id);
            pre.setInt(3, id);

            return pre.executeUpdate();
        }
    }

    public Vector<Blog> getAll() throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);

                vector.add(b);
            }
        }
        return vector;
    }

    public Vector<Blog> getAllApproved() throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = """
                     SELECT 
                         u.username AS poster,
                         
                         b.*,
                     us.username AS author
                     FROM 
                         blog b
                     LEFT JOIN 
                         [user] u ON b.user_id = u.id
                     LEFT JOIN 
                         blog b1 ON b1.id = b.id -- Assuming you want to join the same blog table on an id match (this part needs clarification)
                     LEFT JOIN 
                         [user] us ON us.id = b1.user_id;""";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Blog b = new Blog();
                b.setUsername(rs.getString(1));
                b.setId(rs.getInt(2));
                b.setUser_id(rs.getInt(3));
                b.setTitle(rs.getString(4));
                b.setContent(rs.getString(5));
                b.setCreate_date(rs.getString(6));
                b.setLikes(rs.getInt(7));
                b.setIs_approved(rs.getInt(8));
                b.setIs_banned(rs.getInt(9));
                b.setAuthor_id(rs.getInt(10));
                b.setAuthor_name(rs.getString(11));
                if (b.getIs_approved() == 1) {
                    vector.add(b);
                }
            }
        }
        return vector;
    }

    public int editBlogApproved(int id, int approved) throws SQLException {
        String sql = "UPDATE [dbo].[Blog] "
                + "SET [is_approved] = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, approved);
            pre.setInt(2, id);

            return pre.executeUpdate();
        }
    }

    public void addReason(int id, String reason) throws SQLException {
        String sql = "UPDATE [dbo].[Blog] "
                + "SET [reason_reject] = ? "
                + " WHERE id = ?";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, reason);
            pre.setInt(2, id);
            pre.executeUpdate();

        }
    }

    public Blog getBlog(int id) throws SQLException {
        String sql = """
                     SELECT 
                         b.*,
                     \tus.username AS author
                     FROM 
                         blog b
                     LEFT JOIN 
                         [user] us ON b.author_id = us.id
                     WHERE 
                     \tb.id = ?""";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
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
                    b.setAuthor_name(rs.getString(10));
                    return b;
                } else {
                    throw new SQLException("No blog found with ID: " + id);
                }
            }
        }
    }

    public Vector<Blog> getAllById(int user_id) throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog WHERE [user_id]=?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
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
                b.setReason_reject(rs.getString(10));
                vector.add(b);
            }
        }
        return vector;
    }

    public Vector<Blog> getAllByIdApproved(int user_id) throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog WHERE [user_id]=? AND is_approved=1";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
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
                b.setReason_reject(rs.getString(10));
                vector.add(b);
            }
        }
        return vector;
    }

    public int editBlogRemove(int id) throws SQLException {
        String sql = "UPDATE [dbo].[Blog] "
                + "SET [is_approved] = 4 "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);

            return pre.executeUpdate();
        }
    }

    public int getLastInsertedBlog() throws SQLException {
        String sql = "SELECT MAX(ID) AS LastInsertedBlogID FROM Blog";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("LastInsertedBlogID");
                } else {
                    throw new SQLException("Failed to retrieve last inserted blog ID");
                }
            }
        }
    }

    public Vector<Blog> getListBlogByPage(Vector<Blog> list, int start, int end) {
        Vector<Blog> blog = new Vector<>();
        if (list == null) {
            return null;
        }
        for (int i = start; i < end; i++) {
            blog.add(list.get(i));
        }
        return blog;
    }

   

    public Vector<Blog> filterDate() {
        String sql = """
                     SELECT * 
                     FROM Blog b
                     ORDER BY b.create_date DESC, b.likes DESC;""";
        Vector<Blog> vector = new Vector<>();
        try (Statement state
                = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs
                = state.executeQuery(sql)) {
            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);
                if (b.getIs_approved() == 1) {
                    vector.add(b);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(dao.DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Vector<Blog> filterDateSearch(String search) {
        String sql = """
                     SELECT * 
                     FROM Blog b
                     ORDER BY b.create_date DESC, b.likes DESC;""";
        Vector<Blog> vector = new Vector<>();
        try (Statement state
                = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs
                = state.executeQuery(sql)) {
            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);
                if (b.getIs_approved() == 1) {
                    String a = b.getUsername() + b.getContent() + b.getTitle() + b.getCreate_date();
                    if (a.contains(search)) {
                        vector.add(b);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(dao.DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Vector<Blog> filterPopSearch(String search) {
        String sql = """
                     SELECT * 
                     FROM Blog b
                     ORDER BY b.likes DESC, b.create_date DESC;""";
        Vector<Blog> vector = new Vector<>();
        try (Statement state
                = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs
                = state.executeQuery(sql)) {
            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);
                if (b.getIs_approved() == 1) {
                    String a = b.getUsername() + b.getContent() + b.getTitle() + b.getCreate_date();
                    if (a.contains(search)) {
                        vector.add(b);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(dao.DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Vector<Blog> search1(String search) throws SQLException {

        String sql = "SELECT * FROM Blog WHERE LOWER(title) Like LOWER(?) and is_approved = 1";
        Vector<Blog> v = new Vector<>();
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, "%" + search + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);
                v.add(b);
            }
        }
        return v;

    }

    public Vector<Blog> newPaging(int page, Vector<Blog> all) {
        Vector<Blog> vector = new Vector<>();
        try {
            page -= 1;
            page = page * 6;
            for (int i = page; i <= page + 5; i++) {
                vector.add(all.get(i));
                if (i == all.size() - 1) {
                    break;
                }
            }

        } catch (Exception ex) {

        }
        return vector;
    }

    public int countPage(Vector<Blog> all) {
        return all.size();
    }
    
    public Vector<Blog> getAllWaiting() throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog WHERE is_approved=0";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);

                vector.add(b);
            }
        }
        return vector;
    }
    
     public Vector<Blog> getAllNotWaiting() throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog WHERE is_approved!=0";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);

                vector.add(b);
            }
        }
        return vector;
    }
     
     public Vector<Blog> searchThenFilter(String search, String filter) {
        try {
            Vector<Blog> vector = new Vector<>();
            if (filter.equals("none")) {
                vector = search1(search);
            } else if (filter.equals("fdate")) {
                BlogFilterDate d = new BlogFilterDate();
                vector = search1(search);
                Collections.sort(vector, d);
            } else if (filter.equals("fpop")) {
                BlogFilterPop d = new BlogFilterPop();
                vector = search1(search);
                Collections.sort(vector, d);
            }
            return vector;
        } catch (SQLException e) {

        }
        return null;
    }
    
    public static void main(String[] args) {
        DAOBlog dao = new DAOBlog();

        try {
            System.out.println(dao.getListBlogByPage(dao.getAllWaiting(), 1, 6));
            
        } catch (Exception ex) {
            Logger.getLogger(DAOBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
