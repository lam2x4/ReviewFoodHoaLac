/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.DAOBlog;
import dao.DAOUser;
import entity.Blog;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lam1
 */
public class AdminBlogManagement extends HttpServlet {

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
            out.println("<title>Servlet AdminBlogManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminBlogManagement at " + request.getContextPath() + "</h1>");
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
            HttpSession session = request.getSession();
            User admin = (User) session.getAttribute("Admin");
            if (admin == null) {
                response.sendRedirect("home");
                return;
            }
            DAOBlog daoBlog = new DAOBlog();
            DAOUser daoUser = new DAOUser();
            HashMap<Integer, String> blog_User = new HashMap<>();
            HashMap<Integer, String> blog_Approved = new HashMap<>();

            for (Blog blog : daoBlog.getAll()) {
                for (User user : daoUser.getAll()) {
                    if (blog.getUser_id() == user.getId()) {
                        blog_User.put(blog.getUser_id(), user.getUsername());
                    }
                }
            }
            for (Blog blog : daoBlog.getAll()) {
                if (blog.getIs_approved() == 1) {
                    blog_Approved.put(blog.getId(), "Approved");

                } else if (blog.getIs_approved() == 3) {
                    blog_Approved.put(blog.getId(), "Banned");

                } else if (blog.getIs_approved() == 2) {
                    blog_Approved.put(blog.getId(), "Reject");

                }

            }
            
            //Pagination
            int page, numberpage = 6;
            int size = daoBlog.getAllNotWaiting().size();
            int num = (size % 6 == 0 ? (size / 6) : ((size / 6) + 1)); //so trang
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numberpage;
            end = Math.min(page * numberpage, size);
            Vector<Blog> list1 = daoBlog.getListBlogByPage(daoBlog.getAllNotWaiting(), start, end);

            request.setAttribute("page", page);
            request.setAttribute("num", num);

            request.setAttribute("blog_Approved", blog_Approved);
            request.setAttribute("BlogList", list1);
            request.setAttribute("Blog_User", blog_User);
            request.getRequestDispatcher("AdminBlogManagerPage.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminBlogManagement.class.getName()).log(Level.SEVERE, null, ex);
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
