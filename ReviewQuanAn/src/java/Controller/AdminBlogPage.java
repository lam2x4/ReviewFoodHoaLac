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
import java.time.Duration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@WebServlet(name = "AdminBlogPage", urlPatterns = {"/AdminBlogPage"})
public class AdminBlogPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        User admin = (User) session.getAttribute("Admin");
        if (admin == null) {
            response.sendRedirect("home");
            return;
        }
        String service = request.getParameter("service");
        if (service == null) {
            service = "viewPage";
        }
        int blogId = Integer.parseInt(request.getParameter("id"));
        DAOBlog daoBlog = new DAOBlog();
        DAOImages daoImages = new DAOImages();
        DAOComment daoComment = new DAOComment();
        DAOReportType daoType = new DAOReportType();
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
            boolean isBookmarked;
            DAOBookmark daoBookmark = new DAOBookmark();
            isBookmarked = daoBookmark.getOne(curUser.getId(), blogId) != null;
            request.setAttribute("isBookmarked", isBookmarked);
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

        if (service.equals("editComment")) {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            String newContent = request.getParameter("newContent");
            Comment comm = daoComment.getComment(commentId);
            comm.setContent(newContent);
            daoComment.editComment(comm);
            return;
        }

        if (service.equals("deleteComment")) {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            daoComment.deleteComment(commentId);
            return;
        }

        Vector<Comment> comments;
        String sortOption = request.getParameter("sortOption");
        if (sortOption == null) {
            comments = daoComment.findCommentsByBlog_id(blogId);
        } else {
            if (sortOption.equals("popular")) {
                comments = daoComment.getCommentsSortedByLikes(blogId);
            } else {
                comments = daoComment.findCommentsByBlog_id(blogId);
            }
        }

        Vector<Images> imgs = daoImages.findImagesByBlog_id(blogId);
        Vector<String> avatars = new Vector<>();
        Vector<String> convertedDates = new Vector<>();
        Vector<String> commentsInteractionType = new Vector<>();
        for (Comment comm : comments) {
            avatars.add(daoComment.findAvatarByUser_id(comm.getUser_id()));

            if (curUser != null) {
                cl = daoCommentLikes.getCommentLikes(blogId, curUser.getId(), comm.getId()) != null ? daoCommentLikes.getCommentLikes(blogId, curUser.getId(), comm.getId()) : new CommentLikes(blogId, curUser.getId(), comm.getId(), "");
                commentsInteractionType.add(cl.getInteraction_type());
            }
            convertedDates.add(timeAgo(comm.getCreate_date()));

        }

        request.setAttribute("type_list", daoType.getAll());

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
        request.setAttribute("commentsDates", convertedDates);
        request.setAttribute("authorname", b.getAuthor_name());

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

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("CommentFragment.jsp");
            dispatcher.include(request, response);
        } else {
           
            RequestDispatcher dispth = request.getRequestDispatcher("AdminBlogPage.jsp");
            dispth.forward(request, response);
        }
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
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

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

    public static String timeAgo(String date) {
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime dateTime = LocalDateTime.parse(date, myFormat);
        LocalDateTime curDate = LocalDateTime.now();
        long secondsPast = Duration.between(dateTime, curDate).getSeconds();

        if (secondsPast < 60) {
            return secondsPast + " second" + (secondsPast == 1 ? "" : "s") + " ago";
        } else if (secondsPast < 3600) {
            long minutes = secondsPast / 60;
            return minutes + " minute" + (minutes == 1 ? "" : "s") + " ago";
        } else if (secondsPast < 86400) {
            long hours = secondsPast / 3600;
            return hours + " hour" + (hours == 1 ? "" : "s") + " ago";
        } else if (secondsPast < 172800) {
            return "Yesterday";
        } else if (secondsPast < 604800) {
            long days = secondsPast / 86400;
            return days + " day" + (days == 1 ? "" : "s") + " ago";
        } else if (secondsPast < 2592000) {
            long weeks = secondsPast / 604800;
            return weeks + " week" + (weeks == 1 ? "" : "s") + " ago";
        } else if (secondsPast < 31536000) {
            long months = secondsPast / 2592000;
            return months + " month" + (months == 1 ? "" : "s") + " ago";
        } else {
            long years = secondsPast / 31536000;
            return years + " year" + (years == 1 ? "" : "s") + " ago";
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
