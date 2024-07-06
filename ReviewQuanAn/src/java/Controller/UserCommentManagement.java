/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.DAOBlog;
import dao.DAOComment;
import dao.DAOReport;
import dao.DAOReportType;
import dao.DAOUser;
import entity.Blog;
import entity.Comment;
import entity.Report;
import entity.ReportType;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lam1
 */
public class UserCommentManagement extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserCommentManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserCommentManagement at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

            DAOUser daoUser = new DAOUser();
            User user1 = daoUser.getUser(Integer.parseInt(request.getParameter("user_id")));
            HashMap<Integer, String> Comment_User = new HashMap<>();
            HashMap<Integer, String> Comment_Blog = new HashMap<>();
            DAOBlog daoBlog = new DAOBlog();
            DAOComment daoComment = new DAOComment();
            for (Comment comment : daoComment.GetAllById(user1.getId())) {
                for (User user : daoUser.getAll()) {
                    if (comment.getUser_id() == user.getId()) {
                        Comment_User.put(comment.getUser_id(), user.getUsername());
                    }
                }
            }
            for (Comment comment : daoComment.GetAllById(user1.getId())) {
                for (Blog blog : daoBlog.getAll()) {
                    if (comment.getBlog_id() == blog.getId()) {
                        Comment_Blog.put(comment.getBlog_id(), blog.getTitle());
                    }
                }
            }

            request.setAttribute("user", user1);
            request.setAttribute("commentList", daoComment.GetAllById(user1.getId()));
            request.setAttribute("Comment_Blog", Comment_Blog);
            request.setAttribute("Comment_User", Comment_User);
            request.getRequestDispatcher("UserCommentManager.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
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
