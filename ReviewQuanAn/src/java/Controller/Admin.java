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
public class Admin extends HttpServlet {

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
            out.println("<title>Servlet Admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin at " + request.getContextPath() + "</h1>");
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
            HashMap<Integer, String> blog_User = new HashMap<>();
            HashMap<Integer, String> report_User = new HashMap<>();
            HashMap<Integer, String> report_ReportType = new HashMap<>();
            HashMap<Integer, String> report_Blog = new HashMap<>();
            DAOUser daouser = new DAOUser();
            DAOBlog daoblog = new DAOBlog();
            DAOComment daocomment = new DAOComment();
            DAOReport daoreport = new DAOReport();
            DAOReportType daoReportType = new DAOReportType();
            int blogNumber = daoblog.getAll().size();
            int commentNumber = daocomment.viewAll().size();
            int userNumber = daouser.getAll().size();
            for (Blog blog : daoblog.getAll()) {
                for (User user : daouser.getAll()) {
                    if (blog.getUser_id() == user.getId()) {
                        blog_User.put(blog.getUser_id(), user.getUsername());
                    }
                }
            }
            for (Report report : daoreport.getAll()) {
                for (User user : daouser.getAll()) {
                    if (report.getUser_id() == user.getId()) {
                        report_User.put(report.getUser_id(), user.getUsername());
                    }
                }
            }
            for (Report report : daoreport.getAll()) {
                for (ReportType reportType : daoReportType.getAll()) {
                    if (report.getType_id() == reportType.getId()) {
                        report_ReportType.put(report.getType_id(), reportType.getName());
                    }
                }
            }

            for (Report report : daoreport.getAll()) {
                for (Blog blog : daoblog.getAll()) {
                    if (report.getBlog_id() == blog.getId()) {
                        report_Blog.put(report.getBlog_id(), blog.getContent());
                    }
                }
            }

            request.setAttribute("report_Blog", report_Blog);
            request.setAttribute("report_ReportType", report_ReportType);
            request.setAttribute("report_User", report_User);
            request.setAttribute("reportList", daoreport.getAll());
            request.setAttribute("Blog_User", blog_User);
            request.setAttribute("userNumber", userNumber);
            request.setAttribute("blogNumber", blogNumber);
            request.setAttribute("commentNumber", commentNumber);
            request.setAttribute("BlogList", daoblog.getAll());
            request.getRequestDispatcher("Admin.jsp").forward(request, response);
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
