package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.*;
import dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@WebServlet(name = "BlogPageController", urlPatterns = {"/BlogPageController"})
public class BlogPageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "viewPage";
        }
        int blogId = Integer.parseInt(request.getParameter("id"));
        DAOBlog daoBlog = new DAOBlog();
        DAOImages daoImages = new DAOImages();
        DAOComment daoComment = new DAOComment();
        DAOUser daoUser = new DAOUser();
        DAOBlogLikes daoBlogLikes = new DAOBlogLikes();
        DAOCommentLikes daoCommentLikes = new DAOCommentLikes();

        Blog b = daoBlog.getBlog(blogId);
        User u = daoUser.getUser(b.getUser_id());
        BlogLikes bl = new BlogLikes();
        CommentLikes cl = new CommentLikes();

        User curUser = (User) session.getAttribute("Admin");
        if (curUser == null) {
            curUser = (User) session.getAttribute("User");
        }

        if (service.equals("addComment")) {
            handleAddComment(request, response, curUser, blogId, daoComment);
            return;
        }

        if (curUser != null) {
            bl = daoBlogLikes.getBlogLikes(curUser.getId(), blogId);
        }

        if (service.equals("handlePostLikes")) {
            handlePostLikes(request, response, curUser, blogId, daoBlog);
            return;
        }

        if (service.equals("handleCommentLikes")) {
            int comment_id = Integer.parseInt(request.getParameter("commentId"));
            handleCommentLikes(request, response, blogId, curUser, comment_id, daoComment);
            return;
        }

        Vector<Comment> comments = daoComment.findCommentsByBlog_id(blogId);
        Vector<Images> imgs = daoImages.findImagesByBlog_id(blogId);
        Vector<String> avatars = new Vector<>();
        Vector<String> commentsInteractionType = new Vector<>();
        for (Comment comm : comments) {
            avatars.add(daoComment.findAvatarByUser_id(comm.getUser_id()));

            if (curUser != null) {
                cl = daoCommentLikes.getCommentLikes(blogId, curUser.getId(), comm.getId()) != null ? daoCommentLikes.getCommentLikes(blogId, curUser.getId(), comm.getId()) : new CommentLikes(blogId, curUser.getId(), comm.getId(), "");
                commentsInteractionType.add(cl.getInteraction_type());
            }
        }

        request.setAttribute("username", u.getUsername());
        request.setAttribute("publishDate", b.getCreate_date());
        request.setAttribute("profPic", u.getAvatar());

        request.setAttribute("blogId", request.getParameter("id"));
        request.setAttribute("blogTitle", b.getTitle());
        request.setAttribute("blogContent", b.getContent());
        request.setAttribute("blogLikes", b.getLikes());
        request.setAttribute("blogPictures", imgs);
        request.setAttribute("blogComments", comments);
        request.setAttribute("commentAvatars", avatars);

        if (bl != null) {
            request.setAttribute("postLikeInteractionType", bl.getInteraction_type());
        }

        if (!commentsInteractionType.isEmpty()) {
            request.setAttribute("commentsLikeInteractionType", commentsInteractionType);
        }

        if (curUser != null) {
            request.setAttribute("commentProfPic", curUser.getAvatar());
            request.setAttribute("commentUsername", curUser.getUsername());
        }

        RequestDispatcher dispth = request.getRequestDispatcher("BlogPage.jsp");
        dispth.forward(request, response);
    }

    private void handleAddComment(HttpServletRequest request, HttpServletResponse response, User curUser, int blogId, DAOComment daoComment)
            throws IOException, SQLException {
        if (curUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("User not logged in");
            return;
        }

        String content = request.getParameter("comment-input");
        if (content == null || content.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Comment content cannot be empty");
            return;
        }
        
        LocalDateTime curDate = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Comment comm = new Comment();
        comm.setUser_id(curUser.getId());
        comm.setBlog_id(blogId);
        comm.setContent(content);
        comm.setCreate_date(curDate.format(myFormat));
        comm.setLikes(0);
        comm.setIs_banned(0);

        daoComment.addComment(comm);

        int id = daoComment.getLastestId();
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(id));

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void handlePostLikes(HttpServletRequest request, HttpServletResponse response, User curUser, int blog_id, DAOBlog daoBlog)
            throws IOException, SQLException {
        DAOBlogLikes dao = new DAOBlogLikes();
        int blogLikes = Integer.parseInt(request.getParameter("blogLikes"));
        String interaction_type = request.getParameter("interactionType");

        try {
            if (interaction_type.equals("nothing")) {
                dao.deleteBlogLikes(curUser.getId(), blog_id);
            } else {
                if (dao.getBlogLikes(curUser.getId(), blog_id) == null) {
                    dao.addBlogLikes(new BlogLikes(curUser.getId(), blog_id, interaction_type));
                } else {
                    dao.updateBlogLikes(new BlogLikes(curUser.getId(), blog_id, interaction_type));
                }
            }
            daoBlog.updateLikes(blog_id, blogLikes);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleCommentLikes(HttpServletRequest request, HttpServletResponse response, int blogId, User curUser, int comment_id, DAOComment daoComment)
            throws IOException, SQLException {
        DAOCommentLikes dao = new DAOCommentLikes();
        int blogLikes = Integer.parseInt(request.getParameter("commentLikes"));
        String interaction_type = request.getParameter("interactionType");

        try {
            if (interaction_type.equals("nothing")) {
                dao.deleteCommentLikes(blogId, curUser.getId(), comment_id);
            } else {
                if (dao.getCommentLikes(blogId, curUser.getId(), comment_id) == null) {
                    dao.addCommentLikes(new CommentLikes(blogId, curUser.getId(), comment_id, interaction_type));
                } else {
                    dao.updateCommentLikes(new CommentLikes(blogId, curUser.getId(), comment_id, interaction_type));
                }
            }
            daoComment.updateLikes(comment_id, blogLikes);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BlogPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(BlogPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
