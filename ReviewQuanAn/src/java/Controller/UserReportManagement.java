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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lam1
 */
public class UserReportManagement extends HttpServlet {

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
            out.println("<title>Servlet UserReportManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserReportManagement at " + request.getContextPath() + "</h1>");
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
            DAOUser daoUser = new DAOUser();
            User user1 = (User) session.getAttribute("User");
            if (request.getParameter("user_id") != null) {
                user1 = daoUser.getUser(Integer.parseInt(request.getParameter("user_id")));
            }
            HashMap<Integer, String> report_User = new HashMap<>();
            HashMap<Integer, String> report_ReportType = new HashMap<>();
            HashMap<Integer, String> report_Blog = new HashMap<>();
            DAOUser daouser = new DAOUser();
            DAOBlog daoblog = new DAOBlog();
            DAOReport daoreport = new DAOReport();
            DAOReportType daoReportType = new DAOReportType();

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
                    if (report.getBlog_id()== blog.getId()) {
                        report_Blog.put(report.getBlog_id(), blog.getTitle());
                    }
                }
            }
            
               //Pagination
            int page, numberpage = 6;
            int size = daoreport.getAllById(user1.getId()).size();
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
            Vector<Report> list1 = daoreport.getListReportByPage(daoreport.getAllById(user1.getId()), start, end);

            request.setAttribute("page", page);
            request.setAttribute("num", num);

            request.setAttribute("user", user1);
            request.setAttribute("report_Blog", report_Blog);
            request.setAttribute("report_ReportType", report_ReportType);
            request.setAttribute("report_User", report_User);
            request.setAttribute("reportList", list1);
            request.getRequestDispatcher("UserReportManager.jsp").forward(request, response);
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
