/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.DAOBlog;
import dao.DAOImages;
import entity.Blog;
import entity.Images;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class BlogEdit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        HttpSession session = request.getSession(true);
        User curUser = (User) session.getAttribute("Admin");
        if (curUser == null) {
            curUser = (User) session.getAttribute("User");
        }
        if (curUser != null) {
            DAOBlog daoBlog = new DAOBlog();
            Blog blog = new Blog();
            try {
                blog = daoBlog.getBlog(blogId);
            } catch (SQLException ex) {
                Logger.getLogger(BlogEdit.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.err.println(blog.getUser_id());
            System.err.println(curUser.getId());
            if (blog.getUser_id() == (curUser.getId())) {
                DAOImages daoImages = new DAOImages();
                Vector<Images> imgs = new Vector<>();
                try {
                    imgs = daoImages.findImagesByBlog_id(blogId);
                } catch (SQLException ex) {
                    Logger.getLogger(BlogEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                request.setAttribute("blogPictures", imgs);
                request.setAttribute("blog", blog);
                
                request.getRequestDispatcher("BlogEdit.jsp").forward(request, response);
            } else {
                response.sendRedirect("home");
            }
        } else {
            response.sendRedirect("home");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
