/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import dao.DAOUser;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;

/**
 *
 * @author ACER
 */
public class Login extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        
        //BEGIN
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        User user = login(account, password);
        
        if (user != null) {
            if (user.getVerify_status() == 0) {
                session.setAttribute("userToVerify", user);
                response.sendRedirect("UserController?service=verify");
            } else {
                java.util.Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    session.removeAttribute(key);
                }
                String[] roles = {"empty", "admin", "customer"};
                String role = roles[user.getRole_id()];
                switch (role) {
                    case "admin":
                        session.setAttribute("Admin", user);
                        response.sendRedirect("AfterLogin.jsp");
                        break;
                    case "customer":
                        session.setAttribute("User", user);
                        response.sendRedirect("home");
                        break;
                    case "empty":
                        response.sendRedirect("LoginPage.jsp?status=1");
                        break;
                    default:
                        response.sendRedirect("LoginPage.jsp?status=2");
                        break;
                }
            }
        } else {
            response.sendRedirect("LoginPage.jsp?status=1");
        }
    }

    public User login(String account, String password) {
        DAOUser dao = new DAOUser();
        Vector<User> vector = dao.getAll();
        for (User user : vector) {
            if ((user.getUsername().equals(account) || user.getEmail().equals(account)) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
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
