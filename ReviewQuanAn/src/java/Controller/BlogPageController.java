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
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "BlogPageController", urlPatterns = {"/BlogPageController"})
public class BlogPageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        int blogId = Integer.parseInt(request.getParameter("id"));
        DAOBlog daoBlog = new DAOBlog();
        DAOImages daoImages = new DAOImages();
        DAOComment daoComment = new DAOComment();
        DAOUser daoUser = new DAOUser();
        
        Blog b = daoBlog.getBlog(blogId);
        User u = daoUser.getUser(b.getUser_id());
        
        String[] ppLinks = {"img/photo_1_2024-06-06_11-09-40.jpg", "img/photo_2_2024-06-06_11-09-40.jpg", "img/photo_3_2024-06-06_11-09-40.jpg"};
        
        Vector<Comment> comments = daoComment.findCommentsById(1);
        Vector<Images> imgs = daoImages.findImagesByBlog_id(1);
        imgs.add(new Images(1, "img/Nahida.jpg"));
        imgs.add(new Images(1, "img/Nilou.jpg"));
        imgs.add(new Images(1, "img/ShirokoTerror.jpg"));
        
        request.setAttribute("username", u.getUsername());
        request.setAttribute("publishDate", b.getCreate_date());
        request.setAttribute("profPic", ppLinks[0]);
        
        request.setAttribute("blogTitle", b.getTitle());
        request.setAttribute("blogContent", b.getContent());
        request.setAttribute("blogLikes", b.getLikes());
        request.setAttribute("blogPictures", imgs);
        request.setAttribute("blogComments", comments);
        
        request.setAttribute("commentProfPic", ppLinks[2]);
        
        RequestDispatcher dispth = request.getRequestDispatcher("BlogPage.jsp");
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
