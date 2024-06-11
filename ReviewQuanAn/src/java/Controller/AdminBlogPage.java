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

@WebServlet(name = "BlogPageController", urlPatterns = {"/BlogPageController"})
public class AdminBlogPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        int blogId = Integer.parseInt(request.getParameter("id"));
        DAOBlog daoBlog = new DAOBlog();
        DAOImages daoImages = new DAOImages();
        DAOComment daoComment = new DAOComment();
        DAOUser daoUser = new DAOUser();

        Blog b = daoBlog.getBlog(blogId);
        User u = daoUser.getUser(b.getUser_id());

        User curUser = (User) session.getAttribute("Admin");
        if (curUser == null) {
            curUser = (User) session.getAttribute("User");
        }

        Vector<Comment> comments = daoComment.findCommentsByBlog_id(blogId);
        Vector<Images> imgs = daoImages.findImagesByBlog_id(blogId);
        Vector<String> avatars = new Vector<>();
        for (Comment comm : comments) {
            avatars.add(daoComment.findAvatarByUser_id(comm.getUser_id()));
        }

        request.setAttribute("username", u.getUsername());
        request.setAttribute("publishDate", b.getCreate_date());
        request.setAttribute("profPic", u.getAvatar());

        request.setAttribute("blogTitle", b.getTitle());
        request.setAttribute("blogContent", b.getContent());
        request.setAttribute("blogLikes", b.getLikes());
        request.setAttribute("blogPictures", imgs);
        request.setAttribute("blogComments", comments);
        request.setAttribute("commentAvatars", avatars);

        request.setAttribute("commentProfPic",  curUser.getAvatar());
        request.setAttribute("commentUsername", curUser.getUsername());

        RequestDispatcher dispth = request.getRequestDispatcher("AdminBlogPage.jsp");
        dispth.forward(request, response);
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
